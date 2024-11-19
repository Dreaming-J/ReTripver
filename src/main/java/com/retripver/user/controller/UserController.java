package com.retripver.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.retripver.global.service.FileManageService;
import com.retripver.user.dto.*;
import com.retripver.user.service.*;

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	private final EmailService emailService;
	private final FileManageService fileManageService;
	
	@Autowired
	public UserController(UserService userService, EmailService emailService, FileManageService fileManageService) {
		this.userService = userService;
		this.emailService = emailService;
		this.fileManageService = fileManageService;
	}
	
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refresh_token", required = false) String refreshToken) {

        String newAccessToken = userService.createAccessToken(refreshToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                "Authorization",
                "Bearer " + newAccessToken
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .build();
    }
	
	// 로그인
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		LoginResponse loginResponse = userService.login(loginRequest);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(
                "Authorization",
                "Bearer " + loginResponse.getAccessToken()
        );

        ResponseCookie responseCookie = ResponseCookie
                .from( // Cookie의 Key, Value 설정.
                        "refresh_token", // Key
                        loginResponse.getRefreshToken() // Value
                )
                .domain("localhost") // Cookie를 사용하는 도메인 (특정 도메인에만 적용하려면 사용. 그렇지 않은 경우 생략)
                .path("/") // 해당 도메인의 경로 (쿠키 사용 범위)
                .httpOnly(true) // HTTP 통신에서만 Cookie를 사용.
                .secure(true) // Set-Cookie
                .maxAge(loginResponse.getMaxAge().getTime() / 1000 / 1000) // RefreshToken과 동일한 시간.
                .sameSite("Strict") // 동일한 사이트에서 사용, None: 동일한 사이트가 아니어도 됨.
                .build();

        // SET_COOKIE2를 사용하면 클라이언트의 쿠키가 변경되지 않음.
        return ResponseEntity
                .status(HttpStatus.OK)
                .headers(httpHeaders)
                .header(HttpHeaders.SET_COOKIE, responseCookie.toString())
                .body(loginResponse);
	} 
	
	// 로그아웃
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestHeader(value = "Authorization", required = false) String authorization, @CookieValue(value = "refresh_token", required = false) String refreshToken) {
		//TODO: 수정 필요
		//userService.signOut(authorization, refreshToken);
        
		return ResponseEntity.ok().build();
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		userService.signup(signupRequest);

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
	
	// 이메일 인증 전송
	@PostMapping("/email")
	public ResponseEntity<?> emailAuth(String email) {
		String result = emailService.sendEmailAuth(email);
		
		return ResponseEntity.ok(result);
	}
	
	// 이메일 인증 확인 
	@PostMapping("/email/verify")
	public ResponseEntity<?> emailAuthVerify(EmailAuthVerifyRequest emailAuthVerifyReqeust) {
		EmailAuthVerifyResponse result = emailService.verifyEmailAuth(emailAuthVerifyReqeust);
		
		return ResponseEntity.ok(result);
	}
	
	// 프로필 등록
	@PutMapping("/profile")
	public ResponseEntity<?> profileUpload(@RequestPart UserProfileRequest userProfileRequest, @RequestPart(value="profileImg", required=false) MultipartFile multipartFile, HttpSession session) {
//		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
//		userProfileRequest.setId(loginUser.getId());
		
		String profileImg = fileManageService.uploadFile(multipartFile);
		userProfileRequest.setProfileImg(profileImg);
		
		userService.profileUpload(userProfileRequest);
		
		// 프로필 등록 실패 에러
		
		return ResponseEntity.ok().build();
	}
	
	// 아이디 찾기
	@PostMapping("/search/id")
	public ResponseEntity<?> searchId(@RequestBody UserSearchIdRequest userSearchIdRequest) {
		String id = userService.searchId(userSearchIdRequest);
		
		if (id == null) {
			return ResponseEntity.ok("존재하지 않는 회원입니다.");
		}

		return ResponseEntity.ok(id);
	}

	// 비밀번호 찾기
	@PostMapping("/search/password")
	public ResponseEntity<?> searchPassword(@RequestBody UserSearchPwdRequest userSearchPwdRequest) {
		// 해당 정보의 유저가 존재하는 지 확인
		boolean isExistUser = userService.searchPassword(userSearchPwdRequest);
		
		if (!isExistUser) {
			// 유저가 존재하지 않으면 존재하지 않는다고 보내기
			
			return ResponseEntity.ok("존재하지 않는 회원입니다.");
		}
		
		
		// 존재한다면 임시 비밀번호 이메일로 전송
		

		return ResponseEntity.ok("임시 비밀번호를 이메일로 전송했습니다.");
	}
	
	// 회원 정보 수정
	@PutMapping
	public ResponseEntity<?> modify(@RequestBody UserModifyRequest userModifyRequest, HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		userModifyRequest.setCurId(loginUser.getId());
		
		// 아이디를 수정하는 경우 이전의 아이디로 정보를 찾아야해서 cur과 new를 구분해야 한다!
		
		
		// 추후 이미지 String을 image 파일로 변경하기!!!!
		
		
		userService.modify(userModifyRequest);
		
		// 오류 추가!
		
		// 다시 세센에 바뀐 아이디 정보로 로그인하기?
		
		loginUser.setId(userModifyRequest.getNewId());
		session.setAttribute("loginUser", loginUser);
		
		
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
	@DeleteMapping
	public ResponseEntity<?> resign(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		userService.resign(loginUser.getId());
		
		return ResponseEntity.ok("회원 탈퇴에 성공했습니다.");
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
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");

		StatusUserInfoResponse statusInfo = userService.statusUserInfo(loginUser.getId());
		
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
