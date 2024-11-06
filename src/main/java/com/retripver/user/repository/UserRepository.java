package com.retripver.user.repository;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;

public interface UserRepository {

	LoginResponse login(LoginRequest loginRequest);

	void signup(SignupRequest signupRequest);
	
	boolean idCheck(String id);

	void modifyProfile(UserProfileRequest userProfileRequest);

	String searchId(UserSearchIdRequest userSearchIdRequest);

	void modify(UserModifyRequest userModifyRequest);

	void modifyPassword(PwdModifyRequest pwdModifyRequset);

	void resign(String id);

	boolean isFollow(String fromId, String toId);

	void unfollow(String fromId, String toId);

	void follow(String fromId, String toId);
	
	StatusUserInfoResponse getStatusInfo(String id);

	

}
