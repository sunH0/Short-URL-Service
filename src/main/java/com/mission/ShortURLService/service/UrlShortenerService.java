package com.mission.ShortURLService.service;

import com.mission.ShortURLService.dto.UrlShortenerRequest;
import com.mission.ShortURLService.dto.UrlShortenerResponse;
import com.mission.ShortURLService.entity.Url;
import com.mission.ShortURLService.repository.UrlShortenerRepository;
import com.mission.ShortURLService.utils.BASE62Utils;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UrlShortenerService {
	private final BASE62Utils base62Utils;
	private final UrlShortenerRepository urlShortenerRepository;

	public UrlShortenerResponse getShortenUrl(UrlShortenerRequest request) {
		Optional<Url> optional = urlShortenerRepository.findByOriginUrl(request.getOriginUrl());
		return new UrlShortenerResponse(
			base62Utils.encode(optional.orElseGet(() -> createNewUrl(request))
									   .getId()
									   .toString()));
	}

	public String getOriginUrl(String shortenUrl) {
		// TODO: 2022-06-02 url combine
		Optional<Url> optional = urlShortenerRepository.findByShortenUrl(
			"http://localhost:8080/redirect/" + shortenUrl);
		// TODO: 2022-06-02 THAT URL'S NEVER BEEN SHORTEN
		return optional.orElseThrow(() -> new RuntimeException()).getOriginUrl();
	}

	private Url createNewUrl(UrlShortenerRequest request) {
		Url url = urlShortenerRepository.save(new Url(request.getOriginUrl()));
		log.info("url Id : {}", url.getId());
		url.setShortenUrl(base62Utils.encode(url.getId().toString()));
		return url;
	}
}
