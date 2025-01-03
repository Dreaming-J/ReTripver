package com.retripver.user.dto;

public class UserInfoResponse {
	
	private String id;
	private String profileImg;
	private String profileDesc;
	private int exp;
	private String achievementTable;
	private int achievementId;
	private String achievementTitle;
	private String achievementImg;
	private TierInfoResponse tierInfo;
	
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
	public String getProfileDesc() {
		return profileDesc;
	}
	public void setProfileDesc(String profileDesc) {
		this.profileDesc = profileDesc;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public String getAchievementTable() {
		return achievementTable;
	}
	public void setAchievementTable(String achievementTable) {
		this.achievementTable = achievementTable;
	}
	public int getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(int achievementId) {
		this.achievementId = achievementId;
	}
	public String getAchievementTitle() {
		return achievementTitle;
	}
	public void setAchievementTitle(String achievementTitle) {
		this.achievementTitle = achievementTitle;
	}
	public String getAchievementImg() {
		return achievementImg;
	}
	public void setAchievementImg(String achievementImg) {
		this.achievementImg = achievementImg;
	}
	public TierInfoResponse getTierInfo() {
		return tierInfo;
	}
	public void setTierInfo(TierInfoResponse tierInfo) {
		this.tierInfo = tierInfo;
	}
	
}
