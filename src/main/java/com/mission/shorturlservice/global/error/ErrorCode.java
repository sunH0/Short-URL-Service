package com.mission.shorturlservice.global.error;

import lombok.Getter;

@Getter
public enum  ErrorCode {

	INVALID_SHORT_URL("유효하지 않은 Short URL 입니다.", 404),
	INTERNAL_SERVER_ERROR("Server Error",500);

	private final String message;
	private final int status;

	ErrorCode(final String message, final int status) {
		this.message = message;
		this.status = status;
	}

}
