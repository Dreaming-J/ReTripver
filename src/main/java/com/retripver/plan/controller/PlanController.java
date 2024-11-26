package com.retripver.plan.controller;

import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retripver.global.dto.CompareResult;
import com.retripver.global.service.FileManageService;
import com.retripver.global.service.ImageComparisonService;
import com.retripver.global.util.JWTUtil;
import com.retripver.global.util.PromptTemplateLoader;
import com.retripver.plan.dto.MissionUploadRequest;
import com.retripver.plan.dto.OptimizeCoursesRequest;
import com.retripver.plan.dto.PlanRequest;
import com.retripver.plan.dto.PlanResponse;
import com.retripver.plan.dto.QuestClearRequest;
import com.retripver.plan.service.PlanService;

@RestController
@RequestMapping("/plan")
public class PlanController {

	private final PlanService planService;
	private final JWTUtil jwtUtil;
	private final ChatModel chatModel;
	private final PromptTemplateLoader promptLoader;
	private final FileManageService fileManageService;
	private final ImageComparisonService imageComparisonService;
	
	@Autowired
	public PlanController(PlanService planService, JWTUtil jwtUtil, ChatModel chatModel, PromptTemplateLoader promptLoader, FileManageService fileManageService, ImageComparisonService imageComparisonService) {
		this.planService = planService;
		this.jwtUtil = jwtUtil;
		this.chatModel = chatModel;
		this.promptLoader = promptLoader;
		this.fileManageService = fileManageService;
		this.imageComparisonService = imageComparisonService;
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
		int planId = planService.makePlan(planRequest);
		
		return ResponseEntity.ok(planId);
	}
	
	@GetMapping("/search/{sidoCode}")
	public ResponseEntity<?> search(@PathVariable("sidoCode") int sidoCode) {
		List<PlanResponse> planList = planService.sidoPlanList(sidoCode);
		
		return ResponseEntity.ok(planList);
	}
	
	@PatchMapping("/quest-clear")
	public ResponseEntity<?> questClear(@RequestBody QuestClearRequest questClearRequest, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		questClearRequest.setUserId(userId);
		
		planService.questClear(questClearRequest);
		
		return ResponseEntity.ok(null);
	}
	
	@PostMapping("/optimizeCourses")
	public ResponseEntity<?> omptimize(@RequestBody OptimizeCoursesRequest OptimizeCoursesRequest) {
		int[] newOrder = planService.optimizeCourses(OptimizeCoursesRequest);
		
		return ResponseEntity.ok(newOrder);
	}
	
	@PostMapping("/review")
	public ResponseEntity<?> createReview(@RequestBody PlanResponse planResponse) {
        try {
        	System.out.println(planResponse);
            // 유저 프롬프트 템플릿 로드 및 변수 설정
            String userPromptTemplate = promptLoader.loadUserPrompt();
            PromptTemplate userTemplate = new PromptTemplate(userPromptTemplate);
            userTemplate.add("plan", planResponse);
            String userCommand = userTemplate.render();

            // 시스템 프롬프트 로드
            String systemPromptTemplate = promptLoader.loadSystemPrompt();
            PromptTemplate systemTemplate = new PromptTemplate(systemPromptTemplate);
            systemTemplate.add("day", 1);
            String systemCommand = systemTemplate.render();
            
            // 메시지 생성
            Message userMessage = new UserMessage(userCommand);
            Message systemMessage = new SystemMessage(systemCommand);
            
            // API 호출
            String response = chatModel.call(userMessage, systemMessage);
            
            return ResponseEntity.ok()
            		.header("Content-Type", "application/json;charset=UTF-8")
            		.body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error processing request: " + e.getMessage());
        }
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> uploadMission(@RequestPart MissionUploadRequest missionUploadRequest, @RequestPart MultipartFile multipartFile, @RequestHeader(value = "Authorization") String authorization) {
		String userId = jwtUtil.extractUserId(authorization, false);
		missionUploadRequest.setUserId(userId);
		
		String imgUrl = fileManageService.uploadFile(multipartFile);
		missionUploadRequest.setUserImg(imgUrl);
		
		planService.uploadMission(missionUploadRequest);
		
		System.out.println(missionUploadRequest);
		
		return ResponseEntity.ok(imgUrl);
	}
	
	@GetMapping("/comparision")
	public ResponseEntity<?> compareImage(@RequestParam String url1, @RequestParam String url2) {
		CompareResult result = imageComparisonService.compareTwoImages(url1, url2);
		
		return ResponseEntity.ok(result);
	}
}
