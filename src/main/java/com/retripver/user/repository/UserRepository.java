package com.retripver.user.repository;

import java.util.List;

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

public interface UserRepository {

	LoginResponse login(LoginRequest loginRequest);

	void signup(SignupRequest signupRequest);
	
	boolean idCheck(String id);

	void modifyProfile(UserProfileRequest userProfileRequest);

	String searchId(UserSearchIdRequest userSearchIdRequest);

	void modify(UserModifyRequest userModifyRequest);

	void modifyPassword(PwdModifyRequest pwdModifyRequset);

	void resign(String id);

	boolean isFollow(FollowRequest followRequest);

	void unfollow(FollowRequest followRequest);

	void follow(FollowRequest followRequest);
	
	StatusUserInfoResponse getStatusInfo(String id);

	List<StatusMapCountResponse> getStatusMapCount(String id);

	UserAchievementResponse getUserAchievement(String id);

	List<UserInfoResponse> getRankByExpUserList();

	List<UserInfoResponse> getRankBySidoCodeList(int sidoCode);

	List<UserInfoResponse> getSearchUserByKeyword(String keyword);

}
