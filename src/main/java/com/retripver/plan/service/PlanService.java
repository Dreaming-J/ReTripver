package com.retripver.plan.service;

import java.util.List;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;

public interface PlanService {

	AttractionResponse getAttraction(int attractionNo);

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	boolean likePlan(int planId, String userId);

	List<PlanResponse> rankPlanList(int page);

	void makePlan(PlanRequest planRequest);

}
