package com.retripver.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.service.PlanService;
import com.retripver.user.dto.LoginResponse;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/plan")
public class PlanController {

	private final PlanService planService;
	
	@Autowired
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<?> planList(@PathVariable("userId") String userId) {
		List<PlanResponse> planList = planService.planList(userId);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/copy/{planId}")
	public ResponseEntity<?> copyPlan(@PathVariable("planId") int planId) {
		PlanResponse planResponse = planService.getPlan(planId);
		
		return ResponseEntity.ok(planResponse);
	}
	
	@GetMapping("/my/like")
	public ResponseEntity<?> likeMyPlans(HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		List<PlanResponse> planList = planService.likePlanList(loginResponse.getId());
		
		return ResponseEntity.ok(planList);
	}
}
