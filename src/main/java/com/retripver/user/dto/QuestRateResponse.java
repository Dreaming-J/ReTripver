package com.retripver.user.dto;

public class QuestRateResponse {

	private int successQuestRate;
	private int successQuestCount;
	private int totalQuestCount;
	
	public int getSuccessQuestRate() {
		return successQuestRate;
	}
	public void setSuccessQuestRate(int successQuestRate) {
		this.successQuestRate = successQuestRate;
	}
	public int getSuccessQuestCount() {
		return successQuestCount;
	}
	public void setSuccessQuestCount(int successQuestCount) {
		this.successQuestCount = successQuestCount;
	}
	public int getTotalQuestCount() {
		return totalQuestCount;
	}
	public void setTotalQuestCount(int totalQuestCount) {
		this.totalQuestCount = totalQuestCount;
	}

}
