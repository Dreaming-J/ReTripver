package com.retripver.user.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retripver.user.controller.UserController;

@Order(1)
@RestControllerAdvice(basePackageClasses = UserController.class, annotations = RestController.class)
public class UserExceptionController {
	
	@ExceptionHandler(NotFoundUserException.class)
	public ResponseEntity<?> notFoundUserHandler() {
		UserErrorResponse userErrorResponse = UserErrorResponse.of(UserErrorCode.NOT_FOUND_USER);
		
		return ResponseEntity.internalServerError().body(userErrorResponse);
	}
}
