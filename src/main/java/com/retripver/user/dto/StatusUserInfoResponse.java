package com.retripver.user.dto;

public class StatusUserInfoResponse {

	private UserInfoResponse userInfo;
	private FollowCountResponse followCount;
	private QuestRateResponse questRate;
	
	public UserInfoResponse getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoResponse userInfo) {
		this.userInfo = userInfo;
	}
	public FollowCountResponse getFollow() {
		return followCount;
	}
	public void setFollow(FollowCountResponse follow) {
		this.followCount = follow;
	}
	public QuestRateResponse getQuestRate() {
		return questRate;
	}
	public void setQuestRate(QuestRateResponse questRate) {
		this.questRate = questRate;
	}
	
}
