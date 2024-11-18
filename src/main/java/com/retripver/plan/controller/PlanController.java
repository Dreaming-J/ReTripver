package com.retripver.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.plan.dto.AttractionResponse;
import com.retripver.plan.dto.PlanRequest;
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
	
	@GetMapping("/attraction/info/{attractionNo}")
	public ResponseEntity<?> attractionInfo(@PathVariable("attractionNo") int attractionNo) {
		AttractionResponse attractionResponse = planService.getAttraction(attractionNo);
		
		return ResponseEntity.ok(attractionResponse);
	}
	
	@GetMapping("/attraction/search/{sidoCode}")
	public ResponseEntity<?> attractionList(@PathVariable("sidoCode") int sidoCode, @RequestParam int page) {
		List<AttractionResponse> attractionList = planService.getAttractions(sidoCode, page);
		
		return ResponseEntity.ok(attractionList);
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<?> planList(@PathVariable("userId") String userId) {
		List<PlanResponse> planList = planService.planList(userId);
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/mylist")
	public ResponseEntity<?> myList(HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		List<PlanResponse> planList = planService.myPlanList(loginResponse.getId());
		
		return ResponseEntity.ok(planList);
	}
	
	@GetMapping("/copy/{planId}")
	public ResponseEntity<?> copyPlan(@PathVariable("planId") int planId) {
		PlanResponse planResponse = planService.getPlan(planId);
		
		return ResponseEntity.ok(planResponse);
	}
	
	@GetMapping("/like")
	public ResponseEntity<?> likeMyPlans(HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		List<PlanResponse> likePlanList = planService.likePlanList(loginResponse.getId());
		
		return ResponseEntity.ok(likePlanList);
	}
	
	@GetMapping("/like/{planId}")
	public ResponseEntity<?> likePlan(@PathVariable("planId") int planId, HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		boolean isLike = planService.likePlan(planId, loginResponse.getId());
		
		return ResponseEntity.ok(isLike);
	}
	
	@GetMapping("/rank")
	public ResponseEntity<?> rank(@RequestParam int page) {
		List<PlanResponse> rankPlanList = planService.rankPlanList(page);
		
		return ResponseEntity.ok(rankPlanList);
	}
	
	@PostMapping("/make")
	public ResponseEntity<?> makePlan(@RequestBody PlanRequest planRequest, HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		planRequest.setUserId(loginResponse.getId());
		planService.makePlan(planRequest);
		
		return ResponseEntity.ok(null);
	}
	
	@GetMapping("/search/{sidoCode}")
	public ResponseEntity<?> search(@PathVariable("sidoCode") int sidoCode) {
		List<PlanResponse> planList = planService.sidoPlanList(sidoCode);
		
		return ResponseEntity.ok(planList);
	}
	
	@PatchMapping("/quest-clear")
	public ResponseEntity<?> questClear(@RequestBody int planId, HttpSession httpSession) {
		LoginResponse loginResponse = (LoginResponse) httpSession.getAttribute("loginUser");
		
		planService.questClear(planId, loginResponse.getId());
		
		return ResponseEntity.ok(null);
	}
}
