package com.retripver.user.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
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
}
