package com.mission.shorturlservice.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import com.mission.shorturlservice.domain.Service.UrlService;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import com.mission.shorturlservice.global.util.Base62Encoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

	@InjectMocks
	private UrlService urlService;

	@Mock
	private UrlRepository urlRepository;

	@Mock
	private Base62Encoder base62Encoder;

	@DisplayName("회원가입 할 수 있다")
	@Test
	public void testSignUp() throws Exception {

	}


}
