package com.retripver.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retripver.auth.dto.LoginResponse;
import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 유저 팔로우/언팔로우
	@PutMapping("/follow/{toId}")
	public ResponseEntity<?> follow(@PathVariable String toId, HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		String fromId = loginUser.getId();
		
		boolean isFollow = userService.follow(new FollowRequest(fromId, toId));
		
		return ResponseEntity.ok(isFollow);
	}
	
	// 상태창 유저 정보
	@GetMapping("/status/info")
	public ResponseEntity<?> statusUser(HttpSession session) {
//		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");

		StatusUserInfoResponse statusInfo = userService.statusUserInfo("test");
		
		return ResponseEntity.ok(statusInfo);
	}
	
	// 상태창 지도
	@GetMapping("/status/map")
	public ResponseEntity<?> statusMap(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		List<StatusMapCountResponse> statusMapCountList = userService.statusMapCount(loginUser.getId());
		
		return ResponseEntity.ok(statusMapCountList);
	}
	
	// 업적 확인
	@GetMapping("/achievement/list")
	public ResponseEntity<?> achievement(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		UserAchievementResponse userAchievement = userService.getUserAchievement(loginUser.getId());
		
		return ResponseEntity.ok(userAchievement);
	}
	
	// 전체 경험치에 대한 랭킹
	@GetMapping("/rank")
	public ResponseEntity<?> rankUserByExp() {
		List<UserInfoResponse> userList = userService.getRankByExpUserList();

		return ResponseEntity.ok(userList);
	}
	
	// 해당 지역을 가장 많이 방문한 횟수
	@GetMapping("/rank/{sidoCode}")
	public ResponseEntity<?> rankUserBySidoCode(@PathVariable int sidoCode) {
		List<UserInfoResponse> userList = userService.getRankBySidoCodeUserList(sidoCode);
		
		return ResponseEntity.ok(userList);
	}
	
	// 유저 검색
	@GetMapping("/search/{keyword}")
	public ResponseEntity<?> searchUser(@PathVariable String keyword) {
		List<UserInfoResponse> userList = userService.getSearchUserByKeyword(keyword);
		
		return ResponseEntity.ok(userList);
	}
	
}
