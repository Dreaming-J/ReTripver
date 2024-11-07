package com.retripver.user.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode {
	
	NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "사용자를 찾을 수 없습니다.");
	
	private Integer httpStatus;
	private String message;
	
	UserErrorCode(Integer httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public Integer getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
