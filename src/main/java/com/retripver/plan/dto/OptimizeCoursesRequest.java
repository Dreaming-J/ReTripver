package com.retripver.plan.dto;

import java.util.List;

public class OptimizeCoursesRequest {
	private int size;
	private List<OptimizeCourseRequest> optimizeCourses;
	private boolean fixFirst;
	private boolean fixLast;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<OptimizeCourseRequest> getOptimizeCourses() {
		return optimizeCourses;
	}
	public void setOptimizeCourses(List<OptimizeCourseRequest> optimizeCourses) {
		this.optimizeCourses = optimizeCourses;
	}
	public boolean isFixFirst() {
		return fixFirst;
	}
	public void setFixFirst(boolean fixFirst) {
		this.fixFirst = fixFirst;
	}
	public boolean isFixLast() {
		return fixLast;
	}
	public void setFixLast(boolean fixLast) {
		this.fixLast = fixLast;
	}
}
