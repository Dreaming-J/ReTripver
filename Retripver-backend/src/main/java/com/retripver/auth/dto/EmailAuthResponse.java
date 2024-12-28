package com.retripver.auth.dto;

import java.sql.Timestamp;

public class EmailAuthResponse {

	private String email;
	private String code;
	private Timestamp completeTime;
	
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
	
	@Override
	public String toString() {
		return "EmailAuthResponse [email=" + email + ", code=" + code + ", completeTime=" + completeTime + "]";
	}
}
