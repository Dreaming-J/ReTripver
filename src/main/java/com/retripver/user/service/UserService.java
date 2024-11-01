package com.retripver.user.service;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;

public interface UserService {

	LoginResponse login(LoginRequest loginRequest);

}
