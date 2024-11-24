package com.retripver.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retripver.auth.dto.LoginRequest;
import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserInfoResponse;
import com.retripver.auth.dto.UserModifyRequest;
import com.retripver.auth.dto.UserProfileRequest;
import com.retripver.auth.dto.UserSearchIdRequest;
import com.retripver.auth.dto.UserSearchPwdRequest;
import com.retripver.auth.exception.DuplicatePasswordException;
import com.retripver.auth.exception.DuplicateSignupException;
import com.retripver.auth.exception.InvalidSignupException;
import com.retripver.auth.exception.NotFoundUserException;
import com.retripver.auth.repository.AuthRepository;
import com.retripver.global.exception.InvalidTokenException;
import com.retripver.global.util.HashUtil;
import com.retripver.global.util.JWTUtil;

@Service
public class AuthServiceImpl implements AuthService {
	
	private final AuthRepository authRepository;
    private final HashUtil hashUtil;
    private final JWTUtil jwtUtil;
	
	@Autowired
	public AuthServiceImpl(AuthRepository authRepository, HashUtil hashUtil, JWTUtil jwtUtil) {
		this.authRepository = authRepository;
		this.hashUtil = hashUtil;
		this.jwtUtil = jwtUtil;
	}

    @Override
    public String createAccessToken(String refreshToken) throws InvalidTokenException {
        String userId = jwtUtil.extractUserId(refreshToken, true);
        
        return jwtUtil.createAccessToken(userId);
    }

	@Override
	public boolean isBlackListToken(String token) {
		return authRepository.getBlackListToken(token);
	}

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		String salt = authRepository.findSaltById(loginRequest.getId());
		
		if (salt == null) {
			throw new NotFoundUserException();
		}
		
		String hashedPassword = hashUtil.encrypt(loginRequest.getPassword(), salt);
		loginRequest.setPassword(hashedPassword);
		
		LoginResponse loginResponse = authRepository.login(loginRequest);
		
		if (loginResponse == null) {
			throw new NotFoundUserException();
		}
		
        String accessToken = jwtUtil.createAccessToken(loginResponse.getId());
        String refreshToken = jwtUtil.createRefreshToken(loginResponse.getId());
        loginResponse.setAccessToken(accessToken);
        
        loginResponse.setRefreshToken(refreshToken);
        loginResponse.setMaxAge(jwtUtil.getMaxAge());
		
		return loginResponse;
	}

	@Override
	public void logout(String authorization, String refreshToken) {
        String refreshTokenUserId = jwtUtil.extractUserId(refreshToken, true);
        Date refreshTokenExpiredAt = jwtUtil.getExpirationTime(refreshToken, true);

        String accessTokenUserId = jwtUtil.extractUserId(authorization, false);
        Date accessTokenExpiredAt = jwtUtil.getExpirationTime(authorization, false);

        authRepository.saveBlackList(refreshTokenUserId, refreshToken, refreshTokenExpiredAt);
        authRepository.saveBlackList(accessTokenUserId, authorization, accessTokenExpiredAt);
	}

	@Override
	public void signup(SignupRequest signupRequest) {
		if (!AuthSignupValidator.isValid(signupRequest)) {
			throw new InvalidSignupException();
		}
		
		if (isExistId(signupRequest.getId()) || isExistEmail(signupRequest.getEmail())) {
			throw new DuplicateSignupException();
		}
		
		String salt = hashUtil.generateSalt();
		String hashedPassword = hashUtil.encrypt(signupRequest.getPassword(), salt);
		signupRequest.setSalt(salt);
		signupRequest.setPassword(hashedPassword);
	
		authRepository.signup(signupRequest);
	}

	@Override
	public void resign(String authorization) {
		String id = jwtUtil.extractUserId(authorization, false);
		
		authRepository.resign(id);
	}
	
	@Override
	public boolean isExistId(String id) {
		return authRepository.isExistId(id);
	}
	
	@Override
	public boolean isExistEmail(String email) {
		return authRepository.isExistEmail(email);
	}

	@Override
	public void profileUpload(UserProfileRequest userProfileRequest) {
		authRepository.modifyProfile(userProfileRequest);
	}

	@Override
	public String searchId(UserSearchIdRequest userSearchIdRequest) {
		return authRepository.searchId(userSearchIdRequest);
	}
	
	@Override
	public boolean searchPassword(UserSearchPwdRequest userSearchPwdRequest) {
		return authRepository.searchPassword(userSearchPwdRequest);
	}

	@Override
	public void modify(UserModifyRequest userModifyRequest) {
		if(!AuthSignupValidator.isValid(userModifyRequest)) {
			throw new InvalidSignupException();
		}
		
		authRepository.modify(userModifyRequest);
	}

	@Override
	public void modifyPassword(PwdModifyRequest pwdModifyRequset) {
		if (!AuthSignupValidator.isValidPassword(pwdModifyRequset)) {
			throw new InvalidSignupException();
		}
		
		String curPassword = authRepository.selectPasswordById(pwdModifyRequset.getId());
		
		if (curPassword.equals(pwdModifyRequset.getNewPassword())) {
			throw new DuplicatePasswordException();
		}
		
		authRepository.modifyPassword(pwdModifyRequset);
	}

	@Override
	public UserInfoResponse getUserInfo(String userId) {
		return authRepository.getUserInfo(userId);
	}

}
