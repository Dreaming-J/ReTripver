package com.retripver.user.repository;

import java.util.List;

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

	int currentQuest(String id);

}
