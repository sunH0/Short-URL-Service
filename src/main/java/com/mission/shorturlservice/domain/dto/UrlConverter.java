package com.mission.shorturlservice.domain.dto;

import com.mission.shorturlservice.domain.entity.Url;
import com.mission.shorturlservice.global.util.UrlCombiner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UrlConverter {

	private final UrlCombiner urlCombiner;

	public ShortsResponse convertToShortsResponse(Url url){
		String shortUrl = urlCombiner.combineShortUrl(url.getShorts());

		return new ShortsResponse(shortUrl, url.getCount());
	}

}
