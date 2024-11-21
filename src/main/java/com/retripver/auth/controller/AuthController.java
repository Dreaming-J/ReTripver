package com.retripver.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retripver.auth.dto.EmailAuthVerifyRequest;
import com.retripver.auth.dto.EmailAuthVerifyResponse;
import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.auth.service.AuthService;
import com.retripver.auth.service.EmailService;
import com.retripver.global.service.FileManageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService authService;
	private final EmailService emailService;
	private final FileManageService fileManageService;
	
	@Autowired
	public AuthController(AuthService authService, EmailService emailService, FileManageService fileManageService) {
		this.authService = authService;
		this.emailService = emailService;
		this.fileManageService = fileManageService;
	}
	
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(value = "refresh_token", required = false) String refreshToken) {
        String newAccessToken = authService.createAccessToken(refreshToken);
        
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
		LoginResponse loginResponse = authService.login(loginRequest);

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
                .sameSite("None") // 동일한 사이트에서 사용, None: 동일한 사이트가 아니어도 됨.
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
		//authService.signOut(authorization, refreshToken);
        
		return ResponseEntity.ok().build();
	}
	
	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {
		authService.signup(signupRequest);

		return ResponseEntity.ok().build();
	}
	
	// 회원 탈퇴
	@DeleteMapping
	public ResponseEntity<?> resign(HttpSession session) {
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		
		authService.resign(loginUser.getId());
		
		return ResponseEntity.ok("회원 탈퇴에 성공했습니다.");
	}
	
	// 아이디 중복 체크
	@GetMapping("/exist/id/{id}")
	public ResponseEntity<?> existId(@PathVariable String id) {
		boolean isExistId = authService.isExistId(id);
		
		return ResponseEntity.ok(isExistId);
	}
	
	// 이메일 중복 체크
	@GetMapping("/exist/email/{email}")
	public ResponseEntity<?> existEmail(@PathVariable String email) {
		boolean isExistEmail = authService.isExistEmail(email);
		
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
		
		authService.profileUpload(userProfileRequest);
		
		// 프로필 등록 실패 에러
		
		return ResponseEntity.ok().build();
	}
	
	// 아이디 찾기
	@PostMapping("/search/id")
	public ResponseEntity<?> searchId(@RequestBody UserSearchIdRequest userSearchIdRequest) {
		String id = authService.searchId(userSearchIdRequest);
		
		if (id == null) {
			return ResponseEntity.ok("존재하지 않는 회원입니다.");
		}

		return ResponseEntity.ok(id);
	}

	// 비밀번호 찾기
	@PostMapping("/search/password")
	public ResponseEntity<?> searchPassword(@RequestBody UserSearchPwdRequest userSearchPwdRequest) {
		// 해당 정보의 유저가 존재하는 지 확인
		boolean isExistUser = authService.searchPassword(userSearchPwdRequest);
		
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
		
		
		authService.modify(userModifyRequest);
		
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
		
		authService.modifyPassword(pwdModifyRequset);
		
		// 오류 추가!
		
		return ResponseEntity.ok().build();
	}
}
