package com.retripver.plan.repository;

import java.util.List;
import java.util.Map;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;

public interface PlanRepository {

	AttractionResponse getAttraction(int attractionNo);

	List<AttractionResponse> getAttractions(Map<String, Object> params);

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	int existPlanLike(Map<String, Object> params);

	List<PlanResponse> rankPlanList(Map<String, Object> params);

	int addPlanLike(Map<String, Object> params);

	int canclePlanLike(Map<String, Object> params);

	void makePlan(PlanRequest planRequest);

	List<PlanResponse> sidoPlanList(int sidoCode);

	int getCourseSize(int planId);

	int getCarryOutCourseSize(int planId);

	int getSumExpOfClearCourses(int planId);

	void gainExp(int gainExp, String userId);
}
