package com.retripver.user.dto;

import java.util.List;

public class UserAchievementResponse {

	List<UserAchievementTierResponse> achievementTierList;
	List<UserAchievementVisitResponse> achievementVisitList;
	
	public List<UserAchievementTierResponse> getAchievementTierList() {
		return achievementTierList;
	}
	public void setAchievementTierList(List<UserAchievementTierResponse> achievementTierList) {
		this.achievementTierList = achievementTierList;
	}
	public List<UserAchievementVisitResponse> getAchievementVisitList() {
		return achievementVisitList;
	}
	public void setAchievementVisitList(List<UserAchievementVisitResponse> achievementVisitList) {
		this.achievementVisitList = achievementVisitList;
	}
	
}
