package com.retripver.plan.service;

import java.util.List;

import com.retripver.plan.dto.PlanResponse;

public interface PlanService {

	List<PlanResponse> planList(String userId);

}
