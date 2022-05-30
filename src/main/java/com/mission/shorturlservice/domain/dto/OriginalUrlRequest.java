package com.mission.shorturlservice.domain.dto;

import com.mission.shorturlservice.domain.entity.Url;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

@Getter
public class OriginalUrlRequest {

	@URL
	@NotBlank
	private final String original;

	public OriginalUrlRequest(String original) {
		this.original = original;
	}

}
