package com.retripver.plan.dto;

import java.util.List;

public class PlanResponse {
	private List<CourseResponse> plan;

	public List<CourseResponse> getPlan() {
		return plan;
	}

	public void setPlan(List<CourseResponse> plan) {
		this.plan = plan;
	}
}
