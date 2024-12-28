package com.retripver.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean follow(FollowRequest followRequest) {
		boolean isFollow = userRepository.isFollow(followRequest);
		
		// 이미 팔로우 했다면 취소
		if (isFollow) {
			userRepository.unfollow(followRequest);

			return false;
		} else { // 노 팔로우라면 팔로우하기
			userRepository.follow(followRequest);
			
			return true;
		}
	}

	@Override
	public StatusUserInfoResponse statusUserInfo(String id) {
		return userRepository.getStatusInfo(id);
	}

	@Override
	public List<StatusMapCountResponse> statusMapCount(String id) {
		return userRepository.getStatusMapCount(id);
	}

	@Override
	public UserAchievementResponse getUserAchievement(String id) {
		return userRepository.getUserAchievement(id);
	}

	@Override
	public List<UserInfoResponse> getRankByExpUserList() {
		return userRepository.getRankByExpUserList();
	}

	@Override
	public List<UserInfoResponse> getRankBySidoCodeUserList(int sidoCode) {
		return userRepository.getRankBySidoCodeList(sidoCode);
	}
	
	@Override
	public List<UserInfoResponse> getSearchUserByKeyword(String keyword) {
		return userRepository.getSearchUserByKeyword(keyword);
	}

	@Override
	public int currentQeust(String id) {
		return userRepository.currentQuest(id);
	}
}
