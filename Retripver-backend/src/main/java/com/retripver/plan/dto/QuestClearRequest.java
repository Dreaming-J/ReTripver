package com.retripver.plan.dto;

public class QuestClearRequest {
	
	private int planId;
	private int gainExp;
	private String userId;
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getGainExp() {
		return gainExp;
	}
	public void setGainExp(int gainExp) {
		this.gainExp = gainExp;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "QuestClearDto [planId=" + planId + ", gainExp=" + gainExp + ", userId=" + userId + "]";
	}
}
