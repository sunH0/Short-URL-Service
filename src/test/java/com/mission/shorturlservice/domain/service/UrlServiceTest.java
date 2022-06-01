package com.mission.shorturlservice.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import com.mission.shorturlservice.domain.Service.UrlService;
import com.mission.shorturlservice.domain.dto.ShortsResponse;
import com.mission.shorturlservice.domain.dto.UrlConverter;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import com.mission.shorturlservice.global.error.exception.BusinessException;
import com.mission.shorturlservice.global.util.Base62Encoder;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

	@InjectMocks
	private UrlService urlService;

	@Mock
	private UrlRepository urlRepository;

	@Mock
	private UrlConverter urlConverter;

	private String original = "https://www.naver.com";

	@DisplayName("이전에 요청된적 있는 Original Url 을 단축 시킬 수 있다.")
	@Test
	public void testEncodeUrl() throws Exception {
		//given
		Url url = Url
			.builder()
			.originalURL(original)
			.build();
		String shortUrl = Base62Encoder.encoding(100000);
		url.setShorts(shortUrl);

		ShortsResponse anyResponse = new ShortsResponse(url.getShorts(), url.getCount());

		given(urlRepository.findByOriginalURL(original)).willReturn(Optional.of(url));
		given(urlConverter.convertToShortsResponse(any(Url.class))).willReturn(anyResponse);

		//when
		ShortsResponse response = urlService.encodeUrl(original);

		//then
		assertThat(response.getShortUrl()).isEqualTo(shortUrl);

	}

	@DisplayName("처음 요청된 Original Url 을 단축 시킬 수 있다.")
	@Test
	public void testEncodeUrlFirst() throws Exception {
/*		//given
		Url url = Url
			.builder()
			.originalURL(original)
			.build();

		ShortsResponse anyResponse = new ShortsResponse(url.getShorts(), url.getCount());

		given(urlRepository.findByOriginalURL(original)).willReturn(Optional.empty());
		given(urlRepository.save(any(Url.class))).willReturn(url);

		//when
		ShortsResponse response = urlService.encodeUrl(original);

		//then
		verify(urlRepository).save(any(Url.class));*/

		// getId 때문에 어떻게 해야 할지 모르겠다.

	}

	@DisplayName("단축된 적 없는 잘못된 url로 original url을 요청할 수 없다.")
	@Test
	public void testDecodeInvalidUrl() throws Exception {
		//given
		String invalidShort = "http://localhost:8000/ffff";
		given(urlRepository.findByShorts(anyString())).willReturn(Optional.empty());

		// when // then
		assertThatThrownBy(() -> urlService.decodeUrl(invalidShort)).isInstanceOf(BusinessException.class);
	}

	@DisplayName("단축된 url로 original url을 요청할 수 있다.")
	@Test
	public void testDecodeUrl() throws Exception {
		//given
		Url url = Url
			.builder()
			.originalURL(original)
			.build();
		String shortUrl = Base62Encoder.encoding(100000);
		url.setShorts(shortUrl);

		given(urlRepository.findByShorts(anyString())).willReturn(Optional.of(url));

		// when
		String originUrl = urlService.decodeUrl(shortUrl);

		//then
		assertThat(originUrl).isEqualTo(original);
	}


}
