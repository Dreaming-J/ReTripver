package com.retripver.user.dto;

import java.sql.Timestamp;

public class EmailCodeRequest {

	private String email;
	private String code;
	private Timestamp completeTime;
	
	public EmailCodeRequest() { }
	public EmailCodeRequest(String email, String code, Timestamp completeTime) {
		this.email = email;
		this.code = code;
		this.completeTime = completeTime;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Timestamp getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(Timestamp completeTime) {
		this.completeTime = completeTime;
	}
	
}
