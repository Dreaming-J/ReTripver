package com.retripver.user.dto;

public class FollowRequest {

	private String fromId;
	private String toId;
	
	public FollowRequest() { }
	
	public FollowRequest(String fromId, String toId) {
		this.fromId = fromId;
		this.toId = toId;
	}
	
	public String getFromId() {
		return fromId;
	}
	public void setFromId(String fromId) {
		this.fromId = fromId;
	}
	public String getToId() {
		return toId;
	}
	public void setToId(String toId) {
		this.toId = toId;
	}
	
}
