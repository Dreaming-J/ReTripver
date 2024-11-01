package com.retripver.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.exception.NotFoundUserException;
import com.retripver.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		LoginResponse loginResponse = userService.login(loginRequest);
		
		session.setAttribute("loginUser", loginResponse);
		
		// 로그인 실패 시, 추가하기 
		
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		
		// 유효성 검사하기
		// 1. 비어있는 값이 있는 지
		// 2. 비밀번호와 비밀번호 체크가 같은지
		// 3. 존재하지 않는 아이디인지
		
		
		userService.signup(signupRequest);
		
		// 로그인 실패시도 생성
		
		return ResponseEntity.ok().build();
	}
	
}
