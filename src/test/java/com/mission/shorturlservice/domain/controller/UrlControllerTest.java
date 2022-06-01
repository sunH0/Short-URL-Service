package com.mission.shorturlservice.domain.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mission.shorturlservice.domain.Service.UrlService;
import com.mission.shorturlservice.domain.dto.OriginalUrlRequest;
import com.mission.shorturlservice.domain.dto.ShortsResponse;
import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.domain.repository.UrlRepository;
import com.mission.shorturlservice.global.util.Base62Encoder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UrlService urlService;

	@Autowired
	private UrlRepository urlRepository;

	@Test
	@DisplayName("처음 요청한 url을 단축시킬 수 있다.")
	public void testGetShortUrlFirst() throws  Exception{

		//given
		String originUrl = "https://www.naver.com";
		OriginalUrlRequest request = new OriginalUrlRequest(originUrl);

		//when
		ResultActions resultActions = mockMvc.perform(post("/shortner")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(request)));

		//then
		resultActions.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.shortUrl").value("http://localhost:8000/shortner/b"))
			.andDo(print());
	}

	@Test
	@DisplayName("이전에 요청한 url을 단축시킬 수 있다.")
	public void testGetShortUrl() throws  Exception{

		//given
		String originUrl = "https://www.naver.com";
		urlService.encodeUrl(originUrl);

		OriginalUrlRequest request = new OriginalUrlRequest(originUrl);

		//when
		ResultActions resultActions = mockMvc.perform(post("/shortner")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(request)));

		//then
		resultActions.andExpect(status().isOk())
					 .andExpect(jsonPath("$.data.shortUrl").value("http://localhost:8000/shortner/b"))
					 .andExpect(jsonPath("$.data.count").value("2"))
					 .andDo(print());
	}

	@Test
	@DisplayName("잘못된 형식의 url은 입력할 수 없다.")
	public void testInvalidUrlRequest() throws  Exception {
		//given
		String originUrl = "www.naver.com";
		OriginalUrlRequest request = new OriginalUrlRequest(originUrl);

		//when
		ResultActions resultActions = mockMvc.perform(post("/shortner")
														  .contentType(MediaType.APPLICATION_JSON)
														  .content(objectMapper.writeValueAsString(request)));

		//then
		resultActions.andExpect(status().is4xxClientError())
					 .andDo(print());
	}

	@Test
	@DisplayName("단축된 url을 통해 원본 url 로 redirect 할 수 있다.")
	public void testredirectUrl() throws  Exception {
		//given
		String originUrl = "https://www.naver.com";

		ShortsResponse encodeUrl = urlService.encodeUrl(originUrl);
		String encoded = encodeUrl.getShortUrl();
		String shortUrl = encoded.replace("http://localhost:8000/shortner/","");

		//when
		ResultActions resultActions = mockMvc.perform(get("/shortner/{shortUrl}",shortUrl)
														  .contentType(MediaType.APPLICATION_JSON));

		//then
		resultActions.andExpect(status().is3xxRedirection())
			.andExpect(redirectedUrl(originUrl))
					 .andDo(print());
	}

}
