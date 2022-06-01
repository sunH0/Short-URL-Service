package com.mission.shorturlservice.domain.dto;

import com.mission.shorturlservice.domain.entity.Url;
import lombok.Getter;

@Getter
public class ShortsResponse {

	private final String shortUrl;
	private final int count;

	public ShortsResponse(String shorts, int count) {
		this.shortUrl = shorts;
		this.count = count;
	}

}
