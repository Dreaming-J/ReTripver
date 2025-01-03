package com.retripver.auth.dto;

public class EmailAuthVerifyResponse {

	private boolean verified;
	private String resultText;
	
	public EmailAuthVerifyResponse() { }
	public EmailAuthVerifyResponse(boolean verified, String resultText) {
		this.verified = verified;
		this.resultText = resultText;
	}
	
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	
	@Override
	public String toString() {
		return "EmailAuthVerifyResponse [verified=" + verified + ", resultText=" + resultText + "]";
	}
}
