package com.retripver.plan.repository;

import java.util.List;
import java.util.Map;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanResponse;

public interface PlanRepository {

	List<PlanResponse> planList(String userId);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	List<PlanResponse> rankPlanList(Map<String, Object> params);

	AttractionResponse getAttraction(int attractionNo);
}
