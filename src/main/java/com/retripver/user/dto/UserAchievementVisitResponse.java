package com.retripver.user.dto;

public class UserAchievementVisitResponse {

	private String achievementName;
	private String achievementContent;
	private String achievementImg;
	private int currentVisitCount;
	private int acquireVisitCount;
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
	public int getCurrentVisitCount() {
		return currentVisitCount;
	}
	public void setCurrentVisitCount(int currentVisitCount) {
		this.currentVisitCount = currentVisitCount;
	}
	public int getAcquireVisitCount() {
		return acquireVisitCount;
	}
	public void setAcquireVisitCount(int acquireVisitCount) {
		this.acquireVisitCount = acquireVisitCount;
	}
	public boolean isAcquire() {
		return isAcquire;
	}
	public void setAcquire(boolean isAcquire) {
		this.isAcquire = isAcquire;
	}

}
