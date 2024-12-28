package com.retripver.auth.service;

import com.retripver.auth.dto.PwdModifyRequest;
import com.retripver.auth.dto.SignupRequest;
import com.retripver.auth.dto.UserModifyRequest;

public class AuthSignupValidator {

	public static boolean isValid(SignupRequest signupReqeust) {
		return isValidId(signupReqeust.getId()) &&
			   isValidPassword(signupReqeust.getPassword(), signupReqeust.getPasswordCheck()) &&
			   isValidName(signupReqeust.getName()) &&
			   isValidEmail(signupReqeust.getEmail());
	}
	
	public static boolean isValid(UserModifyRequest userModifyRequest) {
		return isValidId(userModifyRequest.getNewId()) &&
				   isValidName(userModifyRequest.getName()) &&
				   isValidEmail(userModifyRequest.getEmail());
	}
	
	public static boolean isValidPassword(PwdModifyRequest pwdModifyRequset) {
		return isValidPassword(pwdModifyRequset.getNewPassword(), pwdModifyRequset.getNewPasswordCheck());
	}
	
	private static boolean isValidId(String id) {
		// 공백 검사, 4글자 이상, 20글자 이하, 대소문자+숫자+_.-만 가능
		if (id == null) {
			return false;
		}
		
		// 4글자 이상, 20글자 이하
		if (id.length() < 4 || id.length() > 20) {
			return false;
		}
		
		// 대/소문자, 숫자, _.- 문자만 가능
		if (!id.matches("^[a-zA-Z0-9_.-]+$")) {
			return false;
		}

		return true;
	}
	
	private static boolean isValidPassword(String password, String passwordCheck) {
		// 공백 검사
		if (password == null || passwordCheck == null) {
			return false;
		}
		
		// 비밀번호 체크
		if (!password.equals(passwordCheck)) {
			return false;
		}
		
		// 대/소문자, 숫자, 특수문자(!@#$%^&*?) 포함 여부
		return password.matches(".*[a-zA-Z].*") && // 대소문자 포함
			   password.matches(".*[0-9].*") && // 숫자 포함
			   password.matches("^[a-zA-Z0-9!@#$%^&*?]+$"); // 특수 문자 포함
	}
	
	private static boolean isValidName(String name) {
		// 공백 검사
		if (name == null) {
			return false;
		}
		
		// 한글과 영어만 포함
		if (!name.matches("^[a-zA-Z가-힣]+$")) {
			return false;
		}
		
		return true;
	}
	
	private static boolean isValidEmail(String email) {
		// 공백 검사
		if (email == null) {
			return false;
		}
		
		// 이메일 정규표현식
		if (!email.matches("^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
			return false;
		}
		
		return true;
	}
	
}
