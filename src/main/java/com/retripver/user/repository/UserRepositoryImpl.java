package com.retripver.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.PwdModifyRequest;
import com.retripver.user.dto.SignupRequest;
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
	public void signup(SignupRequest signupRequest) {
		userMapper.insert(signupRequest);
	}
	
	@Override
	public boolean idCheck(String id) {
		int isDuplicated = userMapper.selectCountById(id);
		
		if (isDuplicated == 1) return true;
		return false;
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

	
}
