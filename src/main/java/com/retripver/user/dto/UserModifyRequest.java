package com.retripver.user.dto;

public class UserModifyRequest {

	private String curId;
	private String newId;
	private String name;
	private String email;
	private String profileImg;
	private String profileDesc;
	
	public String getCurId() {
		return curId;
	}
	public void setCurId(String curId) {
		this.curId = curId;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getProfileDesc() {
		return profileDesc;
	}
	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}

}
