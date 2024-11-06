package com.retripver.user.dto;

public class UserInfoResponse {
	
	private String id;
	private String profileImg;
	private String profileDesc;
	private int exp;
	private String achievementTitle;
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
	public String getAchievementTitle() {
		return achievementTitle;
	}
	public void setAchievementTitle(String achievementTitle) {
		this.achievementTitle = achievementTitle;
	}
	public TierInfoResponse getTierInfo() {
		return tierInfo;
	}
	public void setTierInfo(TierInfoResponse tierInfo) {
		this.tierInfo = tierInfo;
	}
	@Override
	public String toString() {
		return "UserInfoResponse [id=" + id + ", profileImg=" + profileImg + ", profileDesc=" + profileDesc + ", exp="
				+ exp + ", achievementTitle=" + achievementTitle + ", tierInfo=" + tierInfo + "]";
	}
	
}
