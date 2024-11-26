package com.retripver.plan.dto;

public class MissionUploadRequest {
	private int planId;
	private int courseId;
	private String userId;
	private String userImg;
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	
	@Override
	public String toString() {
		return "MissionUploadRequest [planId=" + planId + ", courseId=" + courseId + ", userId=" + userId + ", userImg="
				+ userImg + "]";
	}
}
