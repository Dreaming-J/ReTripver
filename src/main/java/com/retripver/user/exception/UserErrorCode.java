package com.retripver.user.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode {
	
	NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "로그인에 실패했습니다."),
	NOT_FOUND_LOGIN_USER(HttpStatus.UNAUTHORIZED.value(), "로그인 후 이용 가능합니다."),
	INVALID_SIGNUP_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "올바르지 않은 입력이 포함되어 있습니다."),
	DUPLICATE_SIGNUP_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "중복된 회원 정보가 포함되어 있습니다."),
	USER_SQL_FAILD(HttpStatus.INTERNAL_SERVER_ERROR.value(), "오류가 발생했습니다. 다시 시도해주세요.");
	
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
