package com.mission.ShortURLService.dto;

import lombok.Getter;

@Getter
public class UrlShortenerResponse {

	private String shortenUrl;

	public UrlShortenerResponse(String shortenUrl) {
		this.shortenUrl = shortenUrl;
	}
}
