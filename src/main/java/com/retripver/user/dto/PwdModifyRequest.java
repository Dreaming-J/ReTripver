package com.retripver.user.dto;

public class PwdModifyRequest {

	private String id;
	private String currentPassword;
	private String newPassword;
	private String newPasswordChecked;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCurrentPassword() {
		return currentPassword;
	}
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPasswordChecked() {
		return newPasswordChecked;
	}
	public void setNewPasswordChecked(String newPasswordChecked) {
		this.newPasswordChecked = newPasswordChecked;
	}
	
}
