package com.retripver.plan.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retripver.plan.controller.PlanController;

@RestControllerAdvice(basePackageClasses = PlanController.class, annotations = RestController.class)
public class PlanControllerException {

	@ExceptionHandler(NotFoundPlanException.class)
	public ResponseEntity<?> notFoundPlanHandler(Exception ex) {
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_FOUND_PLAN);
		
		return ResponseEntity.internalServerError().body(errorResponse);
	}
}
