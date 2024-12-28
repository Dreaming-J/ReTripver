package com.retripver.user.dto;

public class QuestRateResponse {

	private double successQuestRate;
	private int successQuestCount;
	private int totalQuestCount;
	
	public double getSuccessQuestRate() {
		return successQuestRate;
	}
	public void setSuccessQuestRate(double successQuestRate) {
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
	@Override
	public String toString() {
		return "QuestRateResponse [successQuestRate=" + successQuestRate + ", successQuestCount=" + successQuestCount
				+ ", totalQuestCount=" + totalQuestCount + "]";
	}

}
