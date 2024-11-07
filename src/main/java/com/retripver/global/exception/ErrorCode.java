package com.retripver.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	BAD_REQUEST(HttpStatus.INTERNAL_SERVER_ERROR.value(), "잘못된 경로로 접근하였습니다.");
	
	private Integer httpStatus;
	private String message;
	
	ErrorCode(Integer httpStatus, String message) {
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
