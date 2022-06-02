package com.mission.ShortURLService.controller;

import com.mission.ShortURLService.service.UrlShortenerService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redirect")
public class RedirectController {
	private final UrlShortenerService urlShortenerService;

	@GetMapping("/{encoded-url}")
	public void redirect(@PathVariable(value = "encoded-url") String encodedUrl, HttpServletResponse httpServletResponse)
		throws IOException {
		String originUrl = urlShortenerService.getOriginUrl(encodedUrl);
		httpServletResponse.sendRedirect(originUrl);
	}

}
