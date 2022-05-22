package com.mission.shorturlservice.domain.Service;

import com.mission.shorturlservice.domain.dto.ShortsResponse;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import com.mission.shorturlservice.global.error.ErrorCode;
import com.mission.shorturlservice.global.error.exception.BusinessException;
import com.mission.shorturlservice.global.util.Base62;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UrlService {

	private final UrlRepository urlRepository;
	private final Base62 base62;

	@Transactional
	public ShortsResponse encodeUrl(String original){
		if(urlRepository.existsByOriginal(original)){
			Url url = urlRepository.findByOriginal(original);
			url.increaseCount();
			return new ShortsResponse(url.getShorts());
		}else{
			Url url = urlRepository.save(Url.builder().original(original).build());
			String shorts = base62.encoding(url.getIndex());
			url.setShorts(shorts);

			return new ShortsResponse(shorts);
		}
	}

	public String decodeUrl(String shorts){
		return urlRepository.findByShorts(shorts)
							.orElseThrow(()->new BusinessException(ErrorCode.INVALID_SHORT_URL))
							.getOriginal();
	}

}
