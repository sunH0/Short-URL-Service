package com.mission.shorturlservice.domain.dto;

import com.mission.shorturlservice.domain.entity.Url;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Getter
@NoArgsConstructor
public class OriginalUrlRequest {

	@URL
	@NotBlank
	private String original;

	public OriginalUrlRequest(String original) {
		this.original = original;
	}

}
