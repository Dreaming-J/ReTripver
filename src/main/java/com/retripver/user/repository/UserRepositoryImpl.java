package com.retripver.user.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.auth.exception.UserSQLException;
import com.retripver.user.dto.FollowRequest;
import com.retripver.user.dto.StatusMapCountResponse;
import com.retripver.user.dto.StatusUserInfoResponse;
import com.retripver.user.dto.UserAchievementResponse;
import com.retripver.user.dto.UserAchievementTierResponse;
import com.retripver.user.dto.UserAchievementVisitResponse;
import com.retripver.user.dto.UserInfoResponse;
import com.retripver.user.mapper.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private final UserMapper userMapper;
	
	@Autowired
	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public boolean isFollow(FollowRequest followRequest) {
		int isFollow = userMapper.selectCountByFromIdAndToId(followRequest);
		
		if (isFollow == 0) return false;
		return true;
	}

	@Override
	public void unfollow(FollowRequest followRequest) {
		try {
			userMapper.deleteFollow(followRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public void follow(FollowRequest followRequest) {
		try {
			userMapper.insertFollow(followRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}
	
	@Override
	public StatusUserInfoResponse getStatusInfo(String id) {
		StatusUserInfoResponse statusInfo = userMapper.selectUserInfoById(id);
		
		String achievementTitle = getAchievementTitle(statusInfo.getUserInfo());
		statusInfo.getUserInfo().setAchievementTitle(achievementTitle);
		
		String achievementImg = getAchievementImg(statusInfo.getUserInfo());
		statusInfo.getUserInfo().setAchievementImg(achievementImg);
		
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
			
			String achievementImg = getAchievementImg(user);
			user.setAchievementImg(achievementImg);
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
			
			String achievementImg = getAchievementImg(user);
			user.setAchievementImg(achievementImg);
		}
		
		return userList;
	}
	
	@Override
	public List<UserInfoResponse> getSearchUserByKeyword(String keyword) {
		List<UserInfoResponse> userList = userMapper.selectUserSearchByKeyword(keyword);
		
		for (UserInfoResponse user : userList) {
			String achievementTitle = getAchievementTitle(user);
			user.setAchievementTitle(achievementTitle);
			
			String achievementImg = getAchievementImg(user);
			user.setAchievementImg(achievementImg);
		}
		
		return userList;
	}
	
	private String getAchievementTitle(UserInfoResponse user) {
		int achievementId = user.getAchievementId();
		String achievementTable = user.getAchievementTable();
		
		if (achievementId == 0 || achievementTable == null) return null;
		
		return userMapper.selectNameFromAchievementById(achievementId, achievementTable);
	}

	private String getAchievementImg(UserInfoResponse user) {
		int achievementId = user.getAchievementId();
		String achievementTable = user.getAchievementTable();
		
		if (achievementId == 0 || achievementTable == null) return null;
		
		return userMapper.selectImgFromAchievementById(achievementId, achievementTable);
	}
}
