package com.mission.ShortURLService.service;

import com.mission.ShortURLService.dto.ShortenUrlRequest;
import com.mission.ShortURLService.dto.ShortenUrlResponse;
import com.mission.ShortURLService.entity.ShortenUrl;
import com.mission.ShortURLService.repository.UrlShortenerRepository;
import com.mission.ShortURLService.utils.BASE62Utils;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {

	private final BASE62Utils base62Utils;
	private final UrlShortenerRepository urlShortenerRepository;

	public ShortenUrlResponse getShortenUrl(ShortenUrlRequest request) {
		Optional<ShortenUrl> optional = urlShortenerRepository.findByOriginUrl(request.getOriginUrl());
		if (optional.isPresent()) {
			return new ShortenUrlResponse(
				request.getOriginUrl(),
				base62Utils.encode(optional.orElseThrow(() -> new RuntimeException()).getId()));
		}
		ShortenUrl shortenUrl = urlShortenerRepository.save(new ShortenUrl(request.getOriginUrl()));
		return new ShortenUrlResponse(request.getOriginUrl(), base62Utils.encode(shortenUrl.getId()));
	}

	public String getOriginUrl(String encodedUrl) {
		Optional<ShortenUrl> optional = urlShortenerRepository.findById(base62Utils.decode(encodedUrl));
		return optional.orElseThrow(() -> new RuntimeException()).getOriginUrl();
	}
}
