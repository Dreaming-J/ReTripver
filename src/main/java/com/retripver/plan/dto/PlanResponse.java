package com.retripver.plan.dto;

import java.util.List;

public class PlanResponse {
	private int id;
	private String userId;
	private String title;
	private int sidoCode;
	private String sidoName;
	private boolean isClear;
	private boolean isPublic;
	private List<CourseResponse> courses;
	private int likeCount;
	private int rank;
	
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
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
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
	public List<CourseResponse> getCourses() {
		return courses;
	}
	public void setCourses(List<CourseResponse> courses) {
		this.courses = courses;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "PlanResponse [id=" + id + ", userId=" + userId + ", title=" + title + ", sidoCode=" + sidoCode
				+ ", sidoName=" + sidoName + ", isClear=" + isClear + ", isPublic=" + isPublic + ", courses=" + courses
				+ ", likeCount=" + likeCount + ", rank=" + rank + "]";
	}
}
