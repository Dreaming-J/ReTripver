package com.retripver.user.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.stringtemplate.v4.compiler.CodeGenerator.conditional_return;

import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.TierInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserAchievementTierResponse;
import com.retripver.user.dto.UserAchievementVisitResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.dto.UserModifyRequest;
import com.retripver.user.dto.UserProfileRequest;
import com.retripver.user.dto.UserSearchIdRequest;
import com.retripver.user.mapper.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private final UserMapper userMapper;
	
	@Autowired
	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		return userMapper.selectByIdAndPassword(loginRequest);
	}

	@Override
	@Transactional
	public void signup(SignupRequest signupRequest) {
		userMapper.insert(signupRequest);
	}
	
	@Override
	public boolean isExistId(String id) {
		int isExisted = userMapper.selectCountById(id);
		
		if (isExisted == 0) return false;
		return true;
	}
	
	@Override
	public boolean isExistEmail(String email) {
		int isExisted = userMapper.selectCountByEmail(email);
		
		if (isExisted == 0) return false;
		return true;
	}

	@Override
	public void modifyProfile(UserProfileRequest userProfileRequest) {
		userMapper.updateProfile(userProfileRequest);
	}

	@Override
	public String searchId(UserSearchIdRequest userSearchIdRequest) {
		String id = userMapper.selectByNameAndEmail(userSearchIdRequest);
		
		return id;
	}

	@Override
	public void modify(UserModifyRequest userModifyRequest) {
		userMapper.update(userModifyRequest);
	}

	@Override
	public void modifyPassword(PwdModifyRequest pwdModifyRequset) {
		userMapper.updatePassword(pwdModifyRequset);
	}

	@Override
	public void resign(String id) {
		userMapper.deleteUser(id);
	}

	@Override
	public boolean isFollow(FollowRequest followRequest) {
		int isFollow = userMapper.selectCountByFromIdAndToId(followRequest);
		
		if (isFollow == 0) return false;
		return true;
	}

	@Override
	public void unfollow(FollowRequest followRequest) {
		userMapper.deleteFollow(followRequest);
	}

	@Override
	public void follow(FollowRequest followRequest) {
		userMapper.insertFollow(followRequest);
	}
	
	@Override
	public StatusUserInfoResponse getStatusInfo(String id) {
		StatusUserInfoResponse statusInfo = userMapper.selectUserInfoById(id);
		
		String achievementTitle = getAchievementTitle(statusInfo.getUserInfo());
		statusInfo.getUserInfo().setAchievementTitle(achievementTitle);
		
		return statusInfo;
	}

	@Override
	public List<StatusMapCountResponse> getStatusMapCount(String id) {
		return userMapper.selectVisitCountById(id);
	}

	@Override
	public UserAchievementResponse getUserAchievement(String id) {
		UserAchievementResponse userAchievement = new UserAchievementResponse();
		
		List<UserAchievementTierResponse> achievementTierList = userMapper.selectAchievementTierById(id);
		List<UserAchievementVisitResponse> achievementVisitList = userMapper.selectAchievementVisitById(id);
		
		userAchievement.setAchievementTierList(achievementTierList);
		userAchievement.setAchievementVisitList(achievementVisitList);
		
		return userAchievement;
	}

	@Override
	public List<UserInfoResponse> getRankByExpUserList() {
		List<UserInfoResponse> userList = userMapper.selectUserOrderByExp();
		
		for (UserInfoResponse user : userList) {
			String achievementTitle = getAchievementTitle(user);
			
			user.setAchievementTitle(achievementTitle);
		}
		
		return userList;
	}
	
	@Override
	@Transactional
	public List<UserInfoResponse> getRankBySidoCodeList(int sidoCode) {
		List<UserInfoResponse> userList = userMapper.selectUserOrderByVisitCount(sidoCode);
		
		for (UserInfoResponse user : userList) {
			String achievementTitle = getAchievementTitle(user);
			
			user.setAchievementTitle(achievementTitle);
		}
		
		return userList;
	}
	
	@Override
	public List<UserInfoResponse> getSearchUserByKeyword(String keyword) {
		List<UserInfoResponse> userList = userMapper.selectUserSearchByKeyword(keyword);
		
		for (UserInfoResponse user : userList) {
			String achievementTitle = getAchievementTitle(user);
			
			user.setAchievementTitle(achievementTitle);
		}
		
		return userList;
	}
	
	private String getAchievementTitle(UserInfoResponse user) {
		int achievementId = user.getAchievementId();
		String achievementTable = user.getAchievementTable();
		
		if (achievementId == 0 || achievementTable == null) return null;
		
		return userMapper.selectNameFromAchievementById(achievementId, achievementTable);
	}
}
