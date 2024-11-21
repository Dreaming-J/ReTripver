package com.retripver.user.repository;

import java.util.List;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.auth.exception.UserSQLException;
import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;

public interface UserRepository {

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
