package com.retripver.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.exception.NotFoundUserException;
import com.retripver.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) throws NotFoundUserException {
		LoginResponse loginResponse = userRepository.login(loginRequest);
		
		if (loginResponse == null) {
			throw new NotFoundUserException();
		}
		
		return loginResponse;
	}

	@Override
	public void signup(SignupRequest signupRequest) {
		userRepository.signup(signupRequest);
	}

	@Override
	public void profileUpload(UserProfileRequest userProfileRequest) {
		userRepository.modifyProfile(userProfileRequest);
	}

	@Override
	public boolean idCheck(String id) {
		return userRepository.idCheck(id);
	}

	@Override
	public String searchId(UserSearchIdRequest userSearchIdRequest) {
		return userRepository.searchId(userSearchIdRequest);
	}

	@Override
	public void modify(UserModifyRequest userModifyRequest) {
		userRepository.modify(userModifyRequest);
	}

	@Override
	public void modifyPassword(PwdModifyRequest pwdModifyRequset) {
		userRepository.modifyPassword(pwdModifyRequset);
	}

	@Override
	public void resign(String id) {
		userRepository.resign(id);
	}

	@Override
	public boolean follow(String fromId, String toId) {
		boolean isFollow = userRepository.isFollow(fromId, toId);
		
		// 이미 팔로우 했다면 취소
		if (isFollow) {
			userRepository.unfollow(fromId, toId);

			return false;
		} else { // 노 팔로우라면 팔로우하기
			userRepository.follow(fromId, toId);
			
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

}
