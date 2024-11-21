package com.retripver.auth.service;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.global.exception.InvalidTokenException;

public interface AuthService {

	String createAccessToken(String refreshToken) throws InvalidTokenException;

	boolean isBlackListToken(String token);

	LoginResponse login(LoginRequest loginRequest);

	void signup(SignupRequest signupRequest);

	void resign(String id);
	
	boolean isExistId(String id);
	
	boolean isExistEmail(String email);

	void profileUpload(UserProfileRequest userProfileRequest);

	String searchId(UserSearchIdRequest userSearchIdRequest);
	
	boolean searchPassword(UserSearchPwdRequest userSearchPwdRequest);

	void modify(UserModifyRequest userModifyRequest);

	void modifyPassword(PwdModifyRequest pwdModifyRequset);

}
