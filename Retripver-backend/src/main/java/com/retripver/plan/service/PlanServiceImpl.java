package com.retripver.plan.service;

import static com.retripver.global.constant.Constant.PAGE_SIZE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.attraction.repository.AttractionRepository;
import com.retripver.global.util.OptimizeUtil;
import com.retripver.plan.dto.MissionUploadRequest;
import com.retripver.plan.dto.OptimizeCoursesRequest;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.dto.QuestClearRequest;
import com.retripver.plan.exception.FailAddPlanLikeException;
import com.retripver.plan.exception.FailDeletePlanLikeException;
import com.retripver.plan.exception.NotFoundPlanException;
import com.retripver.plan.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	private final PlanRepository planRepository;
	private final AttractionRepository attractionRepository;
	private final OptimizeUtil optimizeUtil;
	
	@Autowired
	public PlanServiceImpl(PlanRepository planRepository, AttractionRepository attractionRepository, OptimizeUtil optimizeUtil) {
		this.planRepository = planRepository;
		this.attractionRepository = attractionRepository;
		this.optimizeUtil = optimizeUtil;
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
	public List<PlanResponse> myPlanListInSido(String userId, int sidoCode) { 
	List<PlanResponse> planList = planRepository.myPlanListInSido(userId, sidoCode);
	
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
	public int makePlan(PlanRequest planRequest) {
		int sidoCode = planRequest.getCourses().stream()
				.map(course -> attractionRepository.getAttraction(course.getAttractionNo()).getAreaCode())
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream()
			    .max(Map.Entry.comparingByValue())
			    .map(Map.Entry::getKey)
			    .orElse(0);
		
		planRequest.setSidoCode(sidoCode);
		return planRepository.makePlan(planRequest);
	}

	@Override
	@Transactional(readOnly = true)
	public List<PlanResponse> sidoPlanList(int sidoCode) {
		return planRepository.sidoPlanList(sidoCode);
	}

	@Override
	@Transactional
	public void questClear(QuestClearRequest questClearRequest) {
//		int carryOutCourseSize = planRepository.getCarryOutCourseSize(planId);
//		if (carryOutCourseSize == 0)
//			throw new NoCarryOutCourseInPlanException();
//		
//		int gainExp = planRepository.getSumExpOfClearCourses(planId);
//		if (gainExp > 0)
//			planRepository.gainExp(gainExp, userId);
		System.out.println(questClearRequest.getGainExp());
		planRepository.questClear(questClearRequest);
		planRepository.gainExp(questClearRequest.getGainExp(), questClearRequest.getUserId());
		planRepository.updateTier(questClearRequest.getUserId());
		planRepository.updateAchievement(questClearRequest.getUserId());
		planRepository.clearCurrentQuest(questClearRequest.getUserId());
	}

	@Override
	public int[] optimizeCourses(OptimizeCoursesRequest optimizeCoursesRequest) {
		return optimizeUtil.optimeRoute(optimizeCoursesRequest);
	}

	@Override
	public void uploadMission(MissionUploadRequest missionUploadRequest) {
		planRepository.uploadMission(missionUploadRequest);
	}
}
