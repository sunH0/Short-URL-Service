package com.mission.shorturlservice.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ShortsResponse {

	private final String shorts;

	public ShortsResponse(String shorts) {
		this.shorts = shorts;
	}

}
