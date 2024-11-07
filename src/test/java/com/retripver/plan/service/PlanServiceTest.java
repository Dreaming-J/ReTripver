package com.retripver.plan.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.exception.NotFoundAttractionException;

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
			assertEquals(8, planList.size());
			assertEquals(3, planList.get(3).getCourses().size());
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
			assertEquals(3, planList.size());
		});
	}
	
	@Test
	@DisplayName("인기 여행 계획 불러오기")
	void rankPlans() {
		List<PlanResponse> planList = planService.rankPlanList(1);
		
		assertAll(() -> {
			assertEquals(8, planList.size());
			assertEquals(3, planList.get(0).getLikeCount());
		});
	}
	
	@Test
	@DisplayName("여행지 정보 불러오기")
	void getAttraction() {
		assertAll(() -> {
			assertNotNull(planService.getAttraction(4486));
			assertThatThrownBy(() -> planService.getAttraction(-1)).isInstanceOf(NotFoundAttractionException.class);
		});
	}
}
