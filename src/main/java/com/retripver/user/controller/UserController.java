package com.retripver.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.dto.UserSearchPwdRequest;
import com.retripver.user.exception.NotFoundUserException;
import com.retripver.user.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
		LoginResponse loginResponse = userService.login(loginRequest);
		
		session.setAttribute("loginUser", loginResponse);
		
		// 세션저장? 해시 저장? jwt저장?

		return ResponseEntity.ok().build();
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate();
		
		return ResponseEntity.ok().build();
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		userService.signup(signupRequest);
		
		// 이메일 인증

		return ResponseEntity.ok().build();
	}
	
	// 아이디 중복 체크
	@GetMapping("/exist/id/{id}")
	public ResponseEntity<?> existId(@PathVariable String id) {
		boolean isExistId = userService.isExistId(id);
		
		return ResponseEntity.ok(isExistId);
	}
	
	// 이메일 중복 체크
	@GetMapping("/exist/email/{email}")
	public ResponseEntity<?> existEmail(@PathVariable String email) {
		boolean isExistEmail = userService.isExistEmail(email);
		
		return ResponseEntity.ok(isExistEmail);
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
	
	// 회원 탈퇴
	@DeleteMapping("/user")
	public ResponseEntity<?> resign(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		userService.resign(loginUser.getId());
		
		// 회원 탍퇴에 성공했습니다 같으 문구 출력?
		// 오류 발생했을 때도!
		
		return ResponseEntity.ok().build();
	}
	
	// 유저 팔로우/언팔로우
	@PutMapping("/follow/{toId}")
	public ResponseEntity<?> follow(@PathVariable String toId, HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		String fromId = loginUser.getId();
		
		boolean isFollow = userService.follow(new FollowRequest(fromId, toId));
		
		// 실패도 하기
		
		
		return ResponseEntity.ok(isFollow);
	}
	
	// 상태창 유저 정보
	@GetMapping("/status/info")
	public ResponseEntity<?> statusUser(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");

		StatusUserInfoResponse statusInfo = userService.statusUserInfo(loginUser.getId());
	
		// 오류 확인
		
		return ResponseEntity.ok(statusInfo);
	}
	
	// 상태창 지도
	@GetMapping("/status/map")
	public ResponseEntity<?> statusMap(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		List<StatusMapCountResponse> statusMapCountList = userService.statusMapCount(loginUser.getId());
		
		// 오류 확인
		
		return ResponseEntity.ok(statusMapCountList);
	}
	
	// 업적 확인
	@GetMapping("/achievement/list")
	public ResponseEntity<?> achievement(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		UserAchievementResponse userAchievement = userService.getUserAchievement("test");
		
		// 오류
		
		return ResponseEntity.ok(userAchievement);
	}
	
	// 전체 경험치에 대한 랭킹
	@GetMapping("/rank")
	public ResponseEntity<?> rankUserByExp() {
		List<UserInfoResponse> userList = userService.getRankByExpUserList();
		
		// 에러
		
		return ResponseEntity.ok(userList);
	}
	
	// 해당 지역을 가장 많이 방문한 횟수
	@GetMapping("/rank/{sidoCode}")
	public ResponseEntity<?> rankUserBySidoCode(@PathVariable int sidoCode) {
		List<UserInfoResponse> userList = userService.getRankBySidoCodeUserList(sidoCode);
		
		// 오류
		
		
		return ResponseEntity.ok(userList);
	}
	
	// 유저 검색
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> searchUser(@PathVariable String keyword) {
		List<UserInfoResponse> userList = userService.getSearchUserByKeyword(keyword);
		
		// 오류
//		
//		select * from users u
//		where u.id like '%m%'
//		order by case
//		when u.id like 'm%' then 1
//		when u.id like '%m%' then 2
//		else 3 end, u.id asc;
		
		return ResponseEntity.ok(userList);
	}
	
}
