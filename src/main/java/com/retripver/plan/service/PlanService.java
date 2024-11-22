package com.retripver.plan.service;

import java.util.List;

import com.retripver.attraction.dto.AttractionResponse;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;

public interface PlanService {

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	boolean likePlan(int planId, String userId);

	List<PlanResponse> rankPlanList(int page);

	void makePlan(PlanRequest planRequest);

	List<PlanResponse> sidoPlanList(int sidoCode);

	void questClear(int planId, String userId);

}
