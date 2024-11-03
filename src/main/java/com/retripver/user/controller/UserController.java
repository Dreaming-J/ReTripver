package com.retripver.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.dto.UserSearchPwdRequest;
import com.retripver.user.exception.NotFoundUserException;
import com.retripver.user.service.UserService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 로그인
	@PostMapping
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		LoginResponse loginResponse = userService.login(loginRequest);
		
		session.setAttribute("loginUser", loginResponse);
		
		// 갑자기 생긴 의문
		// 인스타 st라면 이메일 로그인을 해야하나? 아이디 변경이 가능하니까? 시스템적으로?
		
		return ResponseEntity.ok().build();
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		
		// 유효성 검사하기
		// 1. 비어있는 값이 있는 지
		// 2. 비밀번호와 비밀번호 체크가 같은지
		// 3. 존재하지 않는 아이디인지
		
		
		userService.signup(signupRequest);
		
		// 회원가입 만들기 실패 시 오류 생성
		
		return ResponseEntity.ok().build();
	}
	
	// 아이디 중복 체크
	@GetMapping("/check/{id}")
	public ResponseEntity<?> idCheck(@PathVariable String id) {
		boolean isDuplicated = userService.idCheck(id);
		
		return ResponseEntity.ok(isDuplicated);
	}
	
	// 프로필 등록
	@PutMapping("/profile")
	public ResponseEntity<?> profileUpload(@RequestBody UserProfileRequest userProfileRequest, HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		userProfileRequest.setId(loginUser.getId());
		
		// 추후 이미지를 String에서 파일로 바꾸어야 함!!!
		// crud 확인을 위해 String으로 보낸 것임
		
		userService.profileUpload(userProfileRequest);
		
		// 프로필 등록 실패 에러
		
		return ResponseEntity.ok().build();
	}
	
	// 아이디 찾기
	@PostMapping("/search/id")
	public ResponseEntity<?> searchId(@RequestBody UserSearchIdRequest userSearchIdRequest) {
		String id = userService.searchId(userSearchIdRequest);
		
		// 회원 정보가 존재하지 않을 때
		
		return ResponseEntity.ok(id);
	}

	// 비밀번호 찾기
	@PostMapping("/search/password")
	public ResponseEntity<?> searchPassword(@RequestBody UserSearchPwdRequest userSearchPwdRequest) {
		// 해당 정보의 유저가 존재하는 지 확인
		
		
		// 존재한다면 임시 비밀번호 이메일로 전송
		
		// 회원 정보가 존재하지 않을 때
		
		return ResponseEntity.ok().build();
	}
	
	// 회원 정보 수정
	@PutMapping
	public ResponseEntity<?> modify(@RequestBody UserModifyRequest userModifyRequest, HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		userModifyRequest.setId(loginUser.getId());
		
		// 아이디를 수정하는 경우 이전의 아이디로 정보를 찾아야해서 cur과 new를 구분해야 한다!
		
		// 추후 이미지 String을 image 파일로 변경하기!!!!
		
		// 오류 추가!
		
		userService.modify(userModifyRequest);
		
		return ResponseEntity.ok().build();
	}
	
	// 비밀 번호 변경
	@PutMapping("/user/password")
	public ResponseEntity<?> modifyPassword(@RequestBody PwdModifyRequest pwdModifyRequset,HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		pwdModifyRequset.setId(loginUser.getId());
		
		// 아이디가 기존의 비밀번호와 같은지 확인!
		// 비밀번호와 비밀번호 체크가 같은지 확인!
		
		userService.modifyPassword(pwdModifyRequset);
		
		// 오류 추가!
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
}
