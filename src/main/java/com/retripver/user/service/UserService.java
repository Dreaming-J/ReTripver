package com.retripver.user.service;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.exception.NotFoundUserException;

public interface UserService {

	LoginResponse login(LoginRequest loginRequest);

	void signup(SignupRequest signupRequest);
	
	boolean idCheck(String id);

	void profileUpload(UserProfileRequest userProfileRequest);

	String searchId(UserSearchIdRequest userSearchIdRequest);

	void modify(UserModifyRequest userModifyRequest);

	void modifyPassword(PwdModifyRequest pwdModifyRequset);

	void resign(String id);

	boolean follow(String fromId, String toId);

	

}
