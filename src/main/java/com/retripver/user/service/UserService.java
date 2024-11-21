package com.retripver.user.service;

import java.util.List;

import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;

public interface UserService {

	boolean follow(FollowRequest followRequest);

	StatusUserInfoResponse statusUserInfo(String id);

	List<StatusMapCountResponse> statusMapCount(String id);

	UserAchievementResponse getUserAchievement(String id);
	
	List<UserInfoResponse> getRankByExpUserList();

	List<UserInfoResponse> getRankBySidoCodeUserList(int sidoCode);

	List<UserInfoResponse> getSearchUserByKeyword(String keyword);

}
