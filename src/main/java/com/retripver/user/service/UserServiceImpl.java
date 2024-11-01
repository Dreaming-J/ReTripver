package com.retripver.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		return userRepository.login(loginRequest);
	}

}
