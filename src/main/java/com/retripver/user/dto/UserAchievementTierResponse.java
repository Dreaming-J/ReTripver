package com.retripver.user.dto;

public class UserAchievementTierResponse {

	private String achievementName;
	private String achievementContent;
	private String achievementImg;
	private boolean isAcquire;
	
	public String getAchievementName() {
		return achievementName;
	}
	public void setAchievementName(String achievementName) {
		this.achievementName = achievementName;
	}
	public String getAchievementContent() {
		return achievementContent;
	}
	public void setAchievementContent(String achievementContent) {
		this.achievementContent = achievementContent;
	}
	public String getAchievementImg() {
		return achievementImg;
	}
	public void setAchievementImg(String achievementImg) {
		this.achievementImg = achievementImg;
	}
	public boolean isAcquire() {
		return isAcquire;
	}
	public void setAcquire(boolean isAcquire) {
		this.isAcquire = isAcquire;
	}
	
}
