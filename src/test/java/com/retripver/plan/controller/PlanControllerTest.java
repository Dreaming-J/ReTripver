package com.retripver.plan.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.user.dto.LoginResponse;

@AutoConfigureMockMvc
@SpringBootTest(
		properties = {"spring.config.location=classpath:application.properties"},
		classes = { PlanController.class }
		)
@ComponentScan(basePackages = {"com.retripver"})
public class PlanControllerTest {
	
	private MockMvc mockMvc;
	private static MockHttpSession session;
	
	@Autowired
	public PlanControllerTest(MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}
	
	@BeforeAll
	static void setUp() {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setId("test");
		loginResponse.setProfeImg("/");
		
		session = new MockHttpSession();
		session.setAttribute("loginUser", loginResponse);
	}
	
	@AfterAll
	static void clear() {
		session.clearAttributes();
	}
	
	@Test
	@DisplayName("특정 유저의 여행 계획 목록 불러오기")
	@Transactional
	void planList() throws Exception {
		mockMvc.perform(get("/plan/list/test")).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@DisplayName("내 여행 계획 목록 불러오기")
	@Transactional
	void myPlanList() throws Exception {
		mockMvc.perform(get("/plan/mylist").session(session)).andExpect(status().isOk()).andDo(print());
	}


	@Test
	@DisplayName("특정 여행 계획 복사하기")
	@Transactional
	void copyPlan() throws Exception {
		mockMvc.perform(get("/plan/copy/1")).andExpect(status().isOk()).andDo(print());
		mockMvc.perform(get("/plan/copy/-1")).andExpect(status().isInternalServerError()).andDo(print());
	}
	
	@Test
	@DisplayName("좋아요 목록 불러오기")
	@Transactional
	void likePlanList() throws Exception {
		mockMvc.perform(get("/plan/like")
						.session(session))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@DisplayName("인기 여행 계획 불러오기")
	@Transactional
	void rankPlanList() throws Exception {
		mockMvc.perform(get("/plan/rank/1"))
				.andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	@DisplayName("여행지 정보 불러오기")
	@Transactional
	void getAttraction() throws Exception {
		mockMvc.perform(get("/plan/attraction/4486"))
				.andExpect(status().isOk()).andDo(print());
		mockMvc.perform(get("/plan/attraction/-1"))
		.andExpect(status().isInternalServerError()).andDo(print());
	}
}
