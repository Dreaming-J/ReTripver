package com.retripver.plan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	private final PlanRepository planRepository;
	
	@Autowired
	public PlanServiceImpl(PlanRepository planRepository) {
		this.planRepository = planRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> planList(String userId) {
		List<PlanResponse> planList = planRepository.planList(userId);
		
		return planList;
	}
}
