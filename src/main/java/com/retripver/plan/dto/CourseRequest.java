package com.retripver.plan.dto;

public class CourseRequest {
	private int planId;
	private int attractionNo;
	private int courseOrder;
	private String missionImg;
	private int exp;
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getAttractionNo() {
		return attractionNo;
	}
	public void setAttractionNo(int attractionNo) {
		this.attractionNo = attractionNo;
	}
	public int getCourseOrder() {
		return courseOrder;
	}
	public void setCourseOrder(int courseOrder) {
		this.courseOrder = courseOrder;
	}
	public String getMissionImg() {
		return missionImg;
	}
	public void setMissionImg(String missionImg) {
		this.missionImg = missionImg;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	@Override
	public String toString() {
		return "CourseRequest [planId=" + planId + ", attractionNo=" + attractionNo + ", courseOrder=" + courseOrder
				+ ", missionImg=" + missionImg + ", exp=" + exp + "]";
	}
	
}
