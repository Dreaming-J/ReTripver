package com.retripver.plan.service;

import java.util.List;

import com.retripver.plan.dto.MissionUploadRequest;
import com.retripver.plan.dto.OptimizeCoursesRequest;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.dto.QuestClearRequest;

public interface PlanService {

	List<PlanResponse> planList(String userId);

	List<PlanResponse> myPlanList(String userId);

	List<PlanResponse> myPlanListInSido(String userId, int sidoCode);

	PlanResponse getPlan(int planId);

	List<PlanResponse> likePlanList(String userId);

	boolean likePlan(int planId, String userId);

	List<PlanResponse> rankPlanList(int page);

	int makePlan(PlanRequest planRequest);

	List<PlanResponse> sidoPlanList(int sidoCode);

	void questClear(QuestClearRequest questClearRequest);

	int[] optimizeCourses(OptimizeCoursesRequest optimizeCoursesRequest);

	void uploadMission(MissionUploadRequest missionUploadRequest);

}
