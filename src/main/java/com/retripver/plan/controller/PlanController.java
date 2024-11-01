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

@RestController
@RequestMapping("/plan")
public class PlanController {

	private final PlanService planService;
	
	@Autowired
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<?> planList(@PathVariable("userId") int userId) {
		List<PlanResponse> planList = planService.planList(userId);
		
		return ResponseEntity.ok(planList);
	}
}
