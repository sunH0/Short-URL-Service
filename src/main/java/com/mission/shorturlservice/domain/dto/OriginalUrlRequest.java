package com.mission.shorturlservice.domain.dto;

import lombok.Getter;

@Getter
public class OriginalUrlRequest {

	private final String original;

	public OriginalUrlRequest(String original) {
		this.original = original;
	}

}
