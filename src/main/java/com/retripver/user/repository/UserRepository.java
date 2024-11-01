package com.retripver.user.repository;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.SignupRequest;

public interface UserRepository {

	LoginResponse login(LoginRequest loginRequest);

	void insertUser(SignupRequest signupRequest);

}
