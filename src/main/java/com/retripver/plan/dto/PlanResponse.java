package com.retripver.plan.dto;

import java.util.List;

public class PlanResponse {
	private int id;
	private String title;
	private int sidoCode;
	private List<CourseResponse> courses;
	private boolean isClear;
	private boolean isPublic;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public List<CourseResponse> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseResponse> courses) {
		this.courses = courses;
	}
	public boolean isClear() {
		return isClear;
	}
	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}
	public boolean isPublic() {
		return isPublic;
	}
	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
}
