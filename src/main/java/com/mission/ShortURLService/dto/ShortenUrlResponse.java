package com.mission.ShortURLService.dto;

import lombok.Getter;

@Getter
public class ShortenUrlResponse {

	private String shortenUrl;

	public ShortenUrlResponse(String shortenUrl) {
		this.shortenUrl = shortenUrl;
	}
}
