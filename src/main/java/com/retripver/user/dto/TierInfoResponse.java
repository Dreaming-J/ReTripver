package com.retripver.user.dto;

public class TierInfoResponse {

	private int tierId;
	private String tierName;
	private String tierImg;
	private int nextTierExp;
	
	public int getTierId() {
		return tierId;
	}
	public void setTierId(int tierId) {
		this.tierId = tierId;
	}
	public String getTierName() {
		return tierName;
	}
	public void setTierName(String tierName) {
		this.tierName = tierName;
	}
	public String getTierImg() {
		return tierImg;
	}
	public void setTierImg(String tierImg) {
		this.tierImg = tierImg;
	}
	public int getNextTierExp() {
		return nextTierExp;
	}
	public void setNextTierExp(int nextTierExp) {
		this.nextTierExp = nextTierExp;
	}
	@Override
	public String toString() {
		return "TierInfoResponse [tierId=" + tierId + ", tierName=" + tierName + ", tierImg=" + tierImg
				+ ", nextTierExp=" + nextTierExp + "]";
	}
	
	

}
