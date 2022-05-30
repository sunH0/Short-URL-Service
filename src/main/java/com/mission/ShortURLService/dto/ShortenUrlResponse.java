package com.mission.ShortURLService.dto;

import lombok.Getter;

@Getter
public class ShortenUrlResponse {
	private String originUrl;
	private String shortenUrl;

	public ShortenUrlResponse(String originUrl, String shortenUrl) {
		this.originUrl = originUrl;
		this.shortenUrl = shortenUrl;
	}
}
