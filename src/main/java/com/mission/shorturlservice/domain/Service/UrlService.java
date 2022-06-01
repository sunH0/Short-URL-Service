package com.mission.shorturlservice.domain.Service;

import com.mission.shorturlservice.domain.dto.ShortsResponse;
import com.mission.shorturlservice.domain.dto.UrlConverter;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import com.mission.shorturlservice.global.error.ErrorCode;
import com.mission.shorturlservice.global.error.exception.BusinessException;
import com.mission.shorturlservice.global.util.Base62Encoder;
import com.mission.shorturlservice.global.util.UrlCombiner;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UrlService {

	private final UrlRepository urlRepository;
	private final UrlConverter urlConverter;

	@Transactional
	public ShortsResponse encodeUrl(String original){

		Url url = urlRepository
			.findByOriginalURL(original)
			.orElseGet(()->createUrl(original));

		url.increaseCount();
		return urlConverter.convertToShortsResponse(url);
	}

	private Url createUrl(String original){

		Url url = urlRepository.save(Url.builder().originalURL(original).build());
		String shorts = Base62Encoder.encoding(url.getId());
		url.setShorts(shorts);

		return url;
	}

	public String decodeUrl(String shorts){
		return urlRepository.findByShorts(shorts)
							.orElseThrow(()->new BusinessException(ErrorCode.INVALID_SHORT_URL))
							.getOriginalURL();
	}

}
