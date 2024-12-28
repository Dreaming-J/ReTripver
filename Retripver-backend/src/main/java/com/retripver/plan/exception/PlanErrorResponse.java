package com.retripver.plan.exception;

public class PlanErrorResponse {

	private Integer httpStatus;
	private String message;
	
	public PlanErrorResponse(PlanErrorCode planErrorCode) {
		this.httpStatus = planErrorCode.getHttpStatus();
		this.message = planErrorCode.getMessage();
	}
	
	public static PlanErrorResponse of(PlanErrorCode planErrorCode) {
		return new PlanErrorResponse(planErrorCode);
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
