package com.mission.shorturlservice.domain.dto;

import com.mission.shorturlservice.domain.entity.Url;
import lombok.Getter;

@Getter
public class ShortsResponse {

	private final String shorts;
	private final int count;

	private ShortsResponse(String shorts, int count) {
		this.shorts = shorts;
		this.count = count;
	}

	public static ShortsResponse of(Url url) {
		return new ShortsResponse(url.getShorts(), url.getCount());
	}

}
