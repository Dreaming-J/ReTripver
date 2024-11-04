package com.retripver.plan.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.retripver.plan.dto.PlanResponse;

@SpringBootTest(properties = { "spring.config.location=classpath:application.properties" })
public class PlanServiceTest {
	
	private PlanService planService;
	
	@Autowired
	public PlanServiceTest(PlanService planService) {
		this.planService = planService;
	}
	
	@Test
	@DisplayName("특정 유저의 여행 계획 목록 불러오기")
	void listPlan() {
		List<PlanResponse> planList = planService.planList("test");
		
		assertAll(() -> {
			assertEquals(11, planList.size());
			assertEquals(3, planList.get(5).getCourses().size());
		});
	}
	
	@Test
	@DisplayName("특정 여행 계획 복사하기")
	void copyPlan() {
		PlanResponse planResponse = planService.getPlan(6);
		
		assertAll(() -> {
			assertEquals(3, planResponse.getCourses().size());
			assertEquals(3837, planResponse.getCourses().get(0).getAttractionNo());
		});
	}
	
	@Test
	@DisplayName("좋아요 목록 불러오기")
	void likePlans() {
		List<PlanResponse> planList = planService.likePlanList("user");
		
		assertAll(() -> {
			assertEquals(4, planList.size());
		});
	}
}
