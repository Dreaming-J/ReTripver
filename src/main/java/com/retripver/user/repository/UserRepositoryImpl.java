package com.retripver.user.repository;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.user.dto.*;
import com.retripver.user.exception.*;
import com.retripver.user.mapper.UserMapper;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	private final UserMapper userMapper;
	
	@Autowired
	public UserRepositoryImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public void deleteExpiredTokens() {
		userMapper.deleteExpiredTokens();
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		return userMapper.selectByIdAndPassword(loginRequest);
	}

	@Override
	public void signup(SignupRequest signupRequest) {
		try {
			userMapper.insert(signupRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
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
		try {
			userMapper.updateProfile(userProfileRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public String searchId(UserSearchIdRequest userSearchIdRequest) {
		return userMapper.selectByNameAndEmail(userSearchIdRequest);
	}
	
	@Override
	public boolean searchPassword(UserSearchPwdRequest userSearchPwdRequest) {
		String password = userMapper.selectByIdAndNameAndEmail(userSearchPwdRequest);
		
		if (password == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void modify(UserModifyRequest userModifyRequest) {
		try {
			userMapper.update(userModifyRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public void modifyPassword(PwdModifyRequest pwdModifyRequset) {
		try {
			userMapper.updatePassword(pwdModifyRequset);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}
	
	@Override
	public String selectPasswordById(String id) {
		return userMapper.selectPasswordById(id);
	}

	@Override
	public void resign(String id) {
		try {
			userMapper.deleteUser(id);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
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

	@Override
	public String findSaltById(String id) {
		return userMapper.selectSaltById(id);
	}

}
