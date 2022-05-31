package com.mission.shorturlservice.domain.controller;

import com.mission.shorturlservice.domain.Service.UrlService;
import com.mission.shorturlservice.domain.dto.ApiResponse;
import com.mission.shorturlservice.domain.dto.OriginalUrlRequest;
import com.mission.shorturlservice.domain.dto.ShortsResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/shortner")
@RestController
public class UrlController {

	private final UrlService urlService;

	@PostMapping
	public ApiResponse<ShortsResponse> getShortUrl(@RequestBody @Valid OriginalUrlRequest request){

		// trim() : 앞 뒤 공백 제거
		ShortsResponse shortsResponse = urlService.encodeUrl(request.getOriginal().trim());
		return ApiResponse.ok(shortsResponse);
	}

	@GetMapping("/{shortUrl}")
	public void redirectUrl(@PathVariable(value = "shortUrl") String shortUrl, HttpServletResponse response)
		throws IOException {
		String originalUrl = urlService.decodeUrl(shortUrl);
		response.sendRedirect(originalUrl);
	}

}
