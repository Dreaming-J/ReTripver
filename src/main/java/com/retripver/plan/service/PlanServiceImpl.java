package com.retripver.plan.service;

import static com.retripver.global.constant.Constant.PAGE_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.exception.FailAddPlanLikeException;
import com.retripver.plan.exception.FailDeletePlanLikeException;
import com.retripver.plan.exception.NotFoundAttractionException;
import com.retripver.plan.exception.NotFoundPlanException;
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
	public AttractionResponse getAttraction(int attractionNo) {
		AttractionResponse attractionResponse = planRepository.getAttraction(attractionNo);
		
		if (attractionResponse == null)
			throw new NotFoundAttractionException();
		
		return attractionResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> planList(String userId) {
		List<PlanResponse> planList = planRepository.planList(userId);
		
		return planList;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> myPlanList(String userId) {
		List<PlanResponse> planList = planRepository.myPlanList(userId);
		
		return planList;
	}

	@Override
	@Transactional(readOnly = true)
	public PlanResponse getPlan(int planId) {
		PlanResponse planResponse = planRepository.getPlan(planId);
		
		if (planResponse == null)
			throw new NotFoundPlanException();
		
		return planResponse;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> likePlanList(String userId) {
		List<PlanResponse> likePlanList = planRepository.likePlanList(userId);
		
		return likePlanList;
	}

	@Override
	@Transactional(rollbackFor = {FailAddPlanLikeException.class, FailDeletePlanLikeException.class})
	public boolean likePlan(int planId, String userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("planId", planId);
		params.put("userId", userId);
		
		boolean existLike = planRepository.existPlanLike(params) == 1;
		
		if (existLike) {
			int result = planRepository.canclePlanLike(params);
			if (result != 1)
				throw new FailDeletePlanLikeException();
		}
		else {
			int result = planRepository.addPlanLike(params);
			if (result != 1)
				throw new FailAddPlanLikeException();
		}
		
		return !existLike;
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> rankPlanList(int page) {
		Map<String, Object> params = new HashMap<>();
		params.put("page", (page - 1) * PAGE_SIZE);
		params.put("size", PAGE_SIZE);
		
		List<PlanResponse> rankPlanList = planRepository.rankPlanList(params);
		
		return rankPlanList;
	}

	@Override
	@Transactional
	public void makePlan(PlanRequest planRequest) {
		planRepository.makePlan(planRequest);
	}
}
