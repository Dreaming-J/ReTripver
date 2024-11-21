package com.retripver.auth.repository;

import java.util.Date;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.auth.exception.UserSQLException;

public interface AuthRepository {

	void deleteExpiredTokens();

	boolean getBlackListToken(String token);

	LoginResponse login(LoginRequest loginRequest);
	
	void saveBlackList(String userId, String token, Date expiredAt);

	void signup(SignupRequest signupRequest) throws UserSQLException;

	void resign(String id);

	String findSaltById(String id);
	
	boolean isExistId(String id);
	
	boolean isExistEmail(String email);

	void modifyProfile(UserProfileRequest userProfileRequest);

	String searchId(UserSearchIdRequest userSearchIdRequest);
	
	boolean searchPassword(UserSearchPwdRequest userSearchPwdRequest);

	void modify(UserModifyRequest userModifyRequest);

	void modifyPassword(PwdModifyRequest pwdModifyRequset);
	
	String selectPasswordById(String id);

}
