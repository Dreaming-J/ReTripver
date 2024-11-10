package com.retripver.user.exception;

import org.springframework.http.HttpStatus;

public enum UserErrorCode {
	
	NOT_FOUND_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "로그인에 실패했습니다."),
	NOT_FOUND_LOGIN_USER(HttpStatus.UNAUTHORIZED.value(), "로그인 후 이용 가능합니다."),
	INVALID_SIGNUP_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "알맞지 않은 형식의 입력이 포함되어 있습니다."),
	DUPLICATE_SIGNUP_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "이미 존재하는 회원 정보가 포함되어 있습니다."),
	DUPLUCATE_PASSWORD_USER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "기존에 사용하던 비밀번호화 같은 비밀번호를 사용할 수 없습니다."),
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
