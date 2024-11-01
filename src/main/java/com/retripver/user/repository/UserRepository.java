package com.retripver.user.repository;

import com.retripver.user.dto.LoginRequest;
import com.retripver.user.dto.LoginResponse;

public interface UserRepository {

	LoginResponse login(LoginRequest loginRequest);

}
