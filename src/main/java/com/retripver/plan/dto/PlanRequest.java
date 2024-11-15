package com.retripver.plan.dto;

import java.util.List;

public class PlanRequest {
	private int id;
	private String userId;
	private String title;
	private int sidoCode;
	private boolean isPublic;
	private List<CourseRequest> courses;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
	public List<CourseRequest> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseRequest> courses) {
		this.courses = courses;
	}
}
