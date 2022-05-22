package com.mission.shorturlservice.global.error;

import java.util.ArrayList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private String message;
	private int status;

	private ErrorResponse(ErrorCode errorCode) {
		this.message = errorCode.getMessage();
		this.status = errorCode.getStatus();
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
	}

}
