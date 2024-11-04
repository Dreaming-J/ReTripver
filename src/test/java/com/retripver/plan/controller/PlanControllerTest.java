package com.retripver.plan.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.plan.controller.PlanController;
import com.retripver.plan.service.PlanService;

@AutoConfigureMockMvc
@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"},
		classes = { PlanController.class }
		)
@ComponentScan(basePackages = {"com.retripver"})
public class PlanControllerTest {
	
	private MockMvc mockMvc;
	
	@Autowired
	public PlanControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	@Test
	@DisplayName("특정 유저의 여행 계획 목록 불러오기")
	@Transactional
	void planList() throws Exception {
		mockMvc.perform(get("/plan/list/test")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@DisplayName("특정 여행 계획 복사하기")
	@Transactional
	void copyPlan() throws Exception {
		mockMvc.perform(get("/plan/copy/1")).andExpect(status().isOk()).andDo(print());
		mockMvc.perform(get("/plan/copy/-1")).andExpect(status().isInternalServerError()).andDo(print());
	}
}
