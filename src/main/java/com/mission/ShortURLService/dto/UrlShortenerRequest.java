package com.mission.ShortURLService.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;

@Getter
@Validated
public class UrlShortenerRequest {
	@URL
	@NotBlank
	private String originUrl;
}
