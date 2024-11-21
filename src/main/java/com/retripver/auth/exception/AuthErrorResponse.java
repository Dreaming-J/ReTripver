package com.retripver.auth.exception;

public class AuthErrorResponse {

	private Integer httpStatus;
	private String message;
	
	public AuthErrorResponse(AuthErrorCode authErrorCode) {
		this.httpStatus = authErrorCode.getHttpStatus();
		this.message = authErrorCode.getMessage();
	}
	
	public static AuthErrorResponse of(AuthErrorCode authErrorCode) {
		return new AuthErrorResponse(authErrorCode);
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
