package com.mission.shorturlservice.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private String message;
	private int status;
	private List<FieldError> errors;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
	private LocalDateTime serverDatetime;

	private ErrorResponse(ErrorCode errorCode) {
		this.message = errorCode.getMessage();
		this.status = errorCode.getStatus();
		this.errors = new ArrayList<>();
	}

	private ErrorResponse(ErrorCode errorCode, List<FieldError> errors) {
		this.message = errorCode.getMessage();
		this.status = errorCode.getStatus();
		this.errors = errors;
		this.serverDatetime = LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS);
	}

	public static ErrorResponse of(ErrorCode errorCode) {
		return new ErrorResponse(errorCode);
	}

	public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult) {
		return new ErrorResponse(errorCode, FieldError.of(bindingResult));
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FieldError {

		private String field;
		private String value;
		private String reason;

		private FieldError(final String field, final String value, final String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}

		public static List<FieldError> of(
			final String field,
			final String value,
			final String reason
		) {
			List<FieldError> fieldErrors = new ArrayList<>();
			fieldErrors.add(new FieldError(field, value, reason));
			return fieldErrors;
		}

		private static List<FieldError> of(final BindingResult bindingResult) {
			final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();

			return fieldErrors
				.stream()
				.map(fieldError -> new FieldError(
					fieldError.getField(),
					fieldError.getRejectedValue() == null ? "" : fieldError
						.getRejectedValue()
						.toString(),
					fieldError.getDefaultMessage()
				))
				.collect(Collectors.toList());
		}
	}


}
