package com.retripver.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.global.util.JWTUtil;
import com.retripver.plan.dto.OptimizeCoursesRequest;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {

	private final PlanService planService;
	private final JWTUtil jwtUtil;
	
	@Autowired
	public PlanController(PlanService planService, JWTUtil jwtUtil) {
		this.planService = planService;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<?> planList(@PathVariable("userId") String userId) {
		List<PlanResponse> planList = planService.planList(userId);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/list/{userId}/{sidoCode}")
	public ResponseEntity<?> planListInSido(@PathVariable("userId") String userId, @PathVariable int sidoCode) {
		List<PlanResponse> planList = planService.myPlanListInSido(userId, sidoCode);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/mylist")
	public ResponseEntity<?> myList(@RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		List<PlanResponse> planList = planService.myPlanList(userId);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/mylist/{sidoCode}")
	public ResponseEntity<?> myListInSido(@PathVariable int sidoCode, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		List<PlanResponse> planList = planService.myPlanListInSido(userId, sidoCode);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/copy/{planId}")
	public ResponseEntity<?> copyPlan(@PathVariable("planId") int planId) {
		PlanResponse planResponse = planService.getPlan(planId);
		
		return ResponseEntity.ok(planResponse);
	}
	
	@GetMapping("/like")
	public ResponseEntity<?> likeMyPlans(@RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		List<PlanResponse> likePlanList = planService.likePlanList(userId);
		
		return ResponseEntity.ok(likePlanList);
	}
	
	@GetMapping("/like/{planId}")
	public ResponseEntity<?> likePlan(@PathVariable("planId") int planId, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		boolean isLike = planService.likePlan(planId, userId);
		
		return ResponseEntity.ok(isLike);
	}
	
	@GetMapping("/rank")
	public ResponseEntity<?> rank(@RequestParam int page) {
		List<PlanResponse> rankPlanList = planService.rankPlanList(page);
		
		return ResponseEntity.ok(rankPlanList);
	}
	
	@PostMapping("/make")
	public ResponseEntity<?> makePlan(@RequestBody PlanRequest planRequest, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		planRequest.setUserId(userId);
		planService.makePlan(planRequest);
		
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/search/{sidoCode}")
	public ResponseEntity<?> search(@PathVariable("sidoCode") int sidoCode) {
		List<PlanResponse> planList = planService.sidoPlanList(sidoCode);
		
		return ResponseEntity.ok(planList);
	}
	
	@PatchMapping("/quest-clear")
	public ResponseEntity<?> questClear(@RequestBody int planId, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		
		planService.questClear(planId, userId);
		
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/optimizeCourses")
	public ResponseEntity<?> omptimize(@RequestBody OptimizeCoursesRequest OptimizeCoursesRequest) {
		int[] newOrder = planService.optimizeCourses(OptimizeCoursesRequest);
		
		return ResponseEntity.ok(newOrder);
	}
}
