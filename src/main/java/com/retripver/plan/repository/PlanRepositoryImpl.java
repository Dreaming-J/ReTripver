package com.retripver.plan.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public PlanResponse getPlan(int planId) {
		return planMapper.selectPlanByPlanId(planId);
	}

	@Override
	public List<PlanResponse> likePlanList(String userId) {
		return planMapper.selectLikePlansByUserId(userId);
	}
}
