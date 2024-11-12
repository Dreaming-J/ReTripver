package com.retripver.user.service;

import com.retripver.user.dto.EmailAuthVerifyRequest;
import com.retripver.user.dto.EmailAuthVerifyResponse;

import jakarta.mail.MessagingException;

public interface EmailService {

	String sendEmailAuth(String email);
	EmailAuthVerifyResponse verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest);
}
