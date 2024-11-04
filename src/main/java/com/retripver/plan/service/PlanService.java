package com.retripver.plan.service;

import java.util.List;

import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.exception.NotFoundPlanException;

public interface PlanService {

	List<PlanResponse> planList(String userId);

	PlanResponse getPlan(int planId);

}
