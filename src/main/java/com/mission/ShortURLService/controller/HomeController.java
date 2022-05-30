package com.mission.ShortURLService.controller;

import com.mission.ShortURLService.service.UrlShortenerService;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class HomeController {
	private final UrlShortenerService urlShortenerService;

	@GetMapping
	public ModelAndView getHome() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping("/{encoded-url}")
	public void redirect(@PathVariable(value = "encoded-url") String encodedUrl, HttpServletResponse httpServletResponse)
		throws IOException {
		String originUrl = urlShortenerService.getOriginUrl(encodedUrl);
		httpServletResponse.sendRedirect("http://" + originUrl);
	}

}
