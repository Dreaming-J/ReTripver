package com.retripver.user.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retripver.user.controller.UserController;

@RestControllerAdvice(basePackageClasses = UserController.class, annotations = RestController.class)
public class ControllerException {

	@ExceptionHandler(NotFoundUserException.class)
	public ResponseEntity<?> notFoundUserHandler(Exception ex) {
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_FOUND_USER);
		
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
