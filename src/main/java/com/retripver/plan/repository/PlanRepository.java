package com.retripver.plan.repository;

import java.util.List;

import com.retripver.plan.dto.PlanResponse;

public interface PlanRepository {

	List<PlanResponse> planList(int userId);

}
