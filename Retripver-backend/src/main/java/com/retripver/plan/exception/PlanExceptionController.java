package com.retripver.plan.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retripver.plan.controller.PlanController;

@Order(1)
@RestControllerAdvice(basePackageClasses = PlanController.class, annotations = RestController.class)
public class PlanExceptionController {

	@ExceptionHandler(NotFoundPlanException.class)
	public ResponseEntity<?> notFoundPlanHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.NOT_FOUND_PLAN);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}

	@ExceptionHandler(NotFoundAttractionException.class)
	public ResponseEntity<?> notFoundAttractionHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.NOT_FOUND_ATTRACTION);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}

	@ExceptionHandler(FailAddPlanLikeException.class)
	public ResponseEntity<?> failAddPlanLikeHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.FAIL_ADD_PLAN_LIKE);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}

	@ExceptionHandler(FailDeletePlanLikeException.class)
	public ResponseEntity<?> faillDeletePlanLikeHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.FAIL_DELETE_PLAN_LIKE);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}
	
	@ExceptionHandler(NoCarryOutCourseInPlanException.class)
	public ResponseEntity<?> noCarryOutCourseInPlanHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.NO_CARRY_OUT_COURSE_IN_PLAN);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}
	
	@ExceptionHandler(FailQuestClearException.class)
	public ResponseEntity<?> failQuestClearHandler() {
		PlanErrorResponse planErrorResponse = PlanErrorResponse.of(PlanErrorCode.FAIL_QUEST_CLEAR);
		
		return ResponseEntity.internalServerError().body(planErrorResponse);
	}
}
