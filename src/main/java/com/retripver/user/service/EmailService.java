package com.retripver.user.service;

import com.retripver.user.dto.EmailAuthVerifyRequest;

import jakarta.mail.MessagingException;

public interface EmailService {

	String sendEmailAuth(String email);
	boolean verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest);
}
