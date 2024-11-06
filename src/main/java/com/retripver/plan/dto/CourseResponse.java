package com.retripver.plan.dto;

public class CourseResponse {
	private int id;
	private int planId;
	private int attractionNo;
	private AttractionResponse attraction;
	private int courseOrder;
	private String missionImg;
	private String userImg;
	private int exp;
	private boolean isClear;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public AttractionResponse getAttraction() {
		return attraction;
	}
	public void setAttraction(AttractionResponse attraction) {
		this.attraction = attraction;
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
	public String getUserImg() {
		return userImg;
	}
	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public boolean isClear() {
		return isClear;
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
}
