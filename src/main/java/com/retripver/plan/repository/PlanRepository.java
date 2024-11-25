package com.retripver.plan.repository;

import java.util.List;
import java.util.Map;

import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;

public interface PlanRepository {

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	List<PlanResponse> myPlanListInSido(String userId, int sidoCode);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	int existPlanLike(Map<String, Object> params);

	List<PlanResponse> rankPlanList(Map<String, Object> params);

	int addPlanLike(Map<String, Object> params);

	int canclePlanLike(Map<String, Object> params);

	int makePlan(PlanRequest planRequest);

	List<PlanResponse> sidoPlanList(int sidoCode);

	int getCourseSize(int planId);

	int getCarryOutCourseSize(int planId);

	int getSumExpOfClearCourses(int planId);

	void gainExp(int gainExp, String userId);
}
