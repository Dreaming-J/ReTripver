package com.retripver.auth.service;

import com.retripver.auth.dto.EmailAuthVerifyRequest;
import com.retripver.auth.dto.EmailAuthVerifyResponse;

public interface EmailService {

	String sendEmailAuth(String email);
	EmailAuthVerifyResponse verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest);
}
