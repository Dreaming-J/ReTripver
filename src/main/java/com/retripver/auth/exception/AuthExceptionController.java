package com.retripver.auth.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.retripver.auth.controller.AuthController;

@Order(1)
@RestControllerAdvice(basePackageClasses = AuthController.class, annotations = RestController.class)
public class AuthExceptionController {
	
	@ExceptionHandler(NotFoundUserException.class)
	public ResponseEntity<?> notFoundUserHandler() {
		AuthErrorResponse authErrorResponse = AuthErrorResponse.of(AuthErrorCode.NOT_FOUND_USER);
		
		return ResponseEntity.internalServerError().body(authErrorResponse);
	}
	
	@ExceptionHandler(InvalidSignupException.class)
	public ResponseEntity<?> invalidSignupHandler() {
		AuthErrorResponse authErrorResponse = AuthErrorResponse.of(AuthErrorCode.INVALID_SIGNUP_USER);
		
		return ResponseEntity.internalServerError().body(authErrorResponse);
	}
	
	@ExceptionHandler(DuplicateSignupException.class)
	public ResponseEntity<?> duplicateSignupHandler() {
		AuthErrorResponse authErrorResponse = AuthErrorResponse.of(AuthErrorCode.DUPLICATE_SIGNUP_USER);
		
		return ResponseEntity.internalServerError().body(authErrorResponse);
	}
	
	@ExceptionHandler(UserSQLException.class)
	public ResponseEntity<?> userSQLHandler() {
		AuthErrorResponse authErrorResponse = AuthErrorResponse.of(AuthErrorCode.USER_SQL_FAILD);
		
		return ResponseEntity.internalServerError().body(authErrorResponse);
	}
}
