package com.retripver.user.mapper;

public class UserSqlProvider {

	public String selectNameFromAchievementById(int achievementId, String achievementTable) {
		return "SELECT name from " + achievementTable + " WHERE id = " + achievementId;
	}
	
}
