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
	
	@ExceptionHandler(InvalidSignupException.class)
	public ResponseEntity<?> invalidSignupHandler() {
		UserErrorResponse userErrorResponse = UserErrorResponse.of(UserErrorCode.INVALID_SIGNUP_USER);
		
		return ResponseEntity.internalServerError().body(userErrorResponse);
	}
	
	@ExceptionHandler(DuplicateSignupException.class)
	public ResponseEntity<?> duplicateSignupHandler() {
		UserErrorResponse userErrorResponse = UserErrorResponse.of(UserErrorCode.DUPLICATE_SIGNUP_USER);
		
		return ResponseEntity.internalServerError().body(userErrorResponse);
	}
	
	@ExceptionHandler(UserSQLException.class)
	public ResponseEntity<?> userSQLHandler() {
		UserErrorResponse userErrorResponse = UserErrorResponse.of(UserErrorCode.USER_SQL_FAILD);
		
		return ResponseEntity.internalServerError().body(userErrorResponse);
	}
}
