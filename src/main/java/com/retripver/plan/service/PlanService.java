package com.retripver.plan.service;

import java.util.List;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanResponse;

public interface PlanService {

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	List<PlanResponse> rankPlanList(int page);

	AttractionResponse getAttraction(int attractionNo);

}
