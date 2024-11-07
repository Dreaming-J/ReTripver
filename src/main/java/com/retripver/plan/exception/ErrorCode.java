package com.retripver.plan.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
	
	NOT_FOUND_PLAN(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 여행 계획을 찾을 수 없습니다."),
	NOT_FOUND_ATTRACTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 장소를 찾을 수 없습니다.");
	
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
