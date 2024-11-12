package com.retripver.user.dto;

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
	public Timestamp getSendTime() {
		return completeTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.completeTime = sendTime;
	}
	
}
