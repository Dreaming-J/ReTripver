package com.retripver.user.exception;

public class UserErrorResponse {

	private Integer httpStatus;
	private String message;
	
	public UserErrorResponse(UserErrorCode userErrorCode) {
		this.httpStatus = userErrorCode.getHttpStatus();
		this.message = userErrorCode.getMessage();
	}
	
	public static UserErrorResponse of(UserErrorCode userErrorCode) {
		return new UserErrorResponse(userErrorCode);
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
