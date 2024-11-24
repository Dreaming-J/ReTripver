package com.retripver.plan.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.mapper.PlanMapper;

@Repository
public class PlanRepositoryImpl implements PlanRepository {

	private final PlanMapper planMapper;
	
	@Autowired
	public PlanRepositoryImpl(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}

	@Override
	public List<PlanResponse> planList(String userId) {
		return planMapper.selectPlansByUserId(userId);
	}

	@Override
	public List<PlanResponse> myPlanList(String userId) {
		return planMapper.selectMyPlansByUserId(userId);
	}

	@Override
	public List<PlanResponse> myPlanListInSido(String userId, int sidoCode) {
		return planMapper.selectMyPlansByUserIdAndSidoCode(userId, sidoCode);
	}

	@Override
	public PlanResponse getPlan(int planId) {
		return planMapper.selectPlanByPlanId(planId);
	}

	@Override
	public List<PlanResponse> likePlanList(String userId) {
		return planMapper.selectLikePlansByUserId(userId);
	}

	@Override
	public int existPlanLike(Map<String, Object> params) {
		return planMapper.selectLikePlanByUserIdAndPlanId(params);
	}

	@Override
	public List<PlanResponse> rankPlanList(Map<String, Object> params) {
		return planMapper.selectRankPlans(params);
	}

	@Override
	public int addPlanLike(Map<String, Object> params) {
		return planMapper.insertPlanLike(params);
	}

	@Override
	public int canclePlanLike(Map<String, Object> params) {
		return planMapper.deletePlanLike(params);
	}

	@Override
	public void makePlan(PlanRequest planRequest) {
		planMapper.insertPlan(planRequest);
		planMapper.insertCourses(planRequest.getId(), planRequest.getCourses());
	}

	@Override
	public List<PlanResponse> sidoPlanList(int sidoCode) {
		return planMapper.selectPlansBySidoCode(sidoCode);
	}

	@Override
	public int getCourseSize(int planId) {
		return planMapper.selectCourseSizeByPlanId(planId);
	}

	@Override
	public int getCarryOutCourseSize(int planId) {
		return planMapper.selectCarryOutCourseSizeByPlanId(planId);
	}

	@Override
	public int getSumExpOfClearCourses(int planId) {
		return planMapper.selectSumExpOfClearCoursesByPlanId(planId);
	}

	@Override
	public void gainExp(int gainExp, String userId) {
		planMapper.updateExpByUserId(gainExp, userId);
	}
}
