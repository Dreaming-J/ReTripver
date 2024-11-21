package com.retripver.auth.dto;

import java.util.Date;

public class LoginResponse {
	
	private String id;
	private String profileImg;
    private String accessToken;
    private String refreshToken;
    private Date maxAge;
	
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
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    public Date getMaxAge() {
        return maxAge;
    }
    public void setMaxAge(Date maxAge) {
        this.maxAge = maxAge;
    }
}
