package com.retripver.plan.exception;

import org.springframework.http.HttpStatus;

public enum PlanErrorCode {
	FAIL_ADD_PLAN_LIKE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 여행 계획을 좋아요하는데 실패하였습니다."),
	FAIL_DELETE_PLAN_LIKE(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 여행 계획을 좋아요 취소하는데 실패하였습니다."),
	NOT_FOUND_PLAN(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 여행 계획을 찾을 수 없습니다."),
	NOT_FOUND_ATTRACTION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "해당 장소를 찾을 수 없습니다.");
	
	private Integer httpStatus;
	private String message;
	
	PlanErrorCode(Integer httpStatus, String message) {
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
