package com.retripver.auth.repository;

import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserInfoResponse;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.auth.exception.UserSQLException;
import com.retripver.auth.mapper.AuthMapper;

@Repository
public class AuthRepositoryImpl implements AuthRepository {
	
	private final AuthMapper authMapper;
	
	@Autowired
	public AuthRepositoryImpl(AuthMapper authMapper) {
		this.authMapper = authMapper;
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	public void deleteExpiredTokens() {
		authMapper.deleteExpiredTokens();
	}

	@Override
	public boolean getBlackListToken(String token) {
		return authMapper.selectBlackListByToken(token);
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		return authMapper.selectByIdAndPassword(loginRequest);
	}

	@Override
	public void saveBlackList(String userId, String token, Date expiredAt) {
        authMapper.insertBlackList(userId, token, expiredAt);		
	}

	@Override
	public void signup(SignupRequest signupRequest) {
		try {
			authMapper.insert(signupRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public void resign(String id) {
		try {
			authMapper.deleteUser(id);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public String findSaltById(String id) {
		return authMapper.selectSaltById(id);
	}
	
	@Override
	public boolean isExistId(String id) {
		int isExisted = authMapper.selectCountById(id);
		
		if (isExisted == 0) return false;
		return true;
	}
	
	@Override
	public boolean isExistEmail(String email) {
		int isExisted = authMapper.selectCountByEmail(email);
		
		if (isExisted == 0) return false;
		return true;
	}

	@Override
	public void modifyProfile(UserProfileRequest userProfileRequest) {
		try {
			authMapper.updateProfile(userProfileRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public String searchId(UserSearchIdRequest userSearchIdRequest) {
		return authMapper.selectByNameAndEmail(userSearchIdRequest);
	}
	
	@Override
	public boolean searchPassword(UserSearchPwdRequest userSearchPwdRequest) {
		String password = authMapper.selectByIdAndNameAndEmail(userSearchPwdRequest);
		
		if (password == null) {
			return false;
		}
		
		return true;
	}

	@Override
	public void modify(UserModifyRequest userModifyRequest) {
		try {
			authMapper.update(userModifyRequest);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}

	@Override
	public void modifyPassword(PwdModifyRequest pwdModifyRequset) {
		try {
			authMapper.updatePassword(pwdModifyRequset);
		} catch (SQLException e) {
			throw new UserSQLException();
		}
	}
	
	@Override
	public String selectPasswordById(String id) {
		return authMapper.selectPasswordById(id);
	}

	@Override
	public UserInfoResponse getUserInfo(String userId) {
		return authMapper.selectIdAndProfileImageById(userId);
	}

}
