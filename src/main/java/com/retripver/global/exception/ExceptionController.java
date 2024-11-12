package com.retripver.global.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(2)
//@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> error() {
		ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.BAD_REQUEST);
		
		return ResponseEntity.internalServerError().body(errorResponse);
	}
}
