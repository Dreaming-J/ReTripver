package com.retripver.auth.dto;

public class EmailAuthVerifyRequest {

	private String email;
	private String code;
	
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
	
	@Override
	public String toString() {
		return "EmailAuthVerifyRequest [email=" + email + ", code=" + code + "]";
	}
}
