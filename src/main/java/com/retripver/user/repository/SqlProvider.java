package com.retripver.user.repository;

public class SqlProvider {

	public String selectNameFromAchievementById(int achievementId, String achievementTable) {
		return "SELECT name from " + achievementTable + " WHERE id = " + achievementId;
	}
	
}
