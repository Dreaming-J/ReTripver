package com.retripver.auth.dto;

public class UserInfoResponse {

	private String id;
	private String profileImg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	@Override
	public String toString() {
		return "UserInfoResponse [id=" + id + ", profileImg=" + profileImg + "]";
	}
}
