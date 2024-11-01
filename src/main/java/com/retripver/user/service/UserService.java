package com.retripver.user.service;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;
import com.retripver.user.dto.SignupRequest;
import com.retripver.user.exception.NotFoundUserException;

public interface UserService {

	LoginResponse login(LoginRequest loginRequest);

	void signup(SignupRequest signupRequest);

}
