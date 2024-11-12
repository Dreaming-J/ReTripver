package com.retripver.user.service;

import com.retripver.user.dto.EmailAuthVerifyRequest;

import jakarta.mail.MessagingException;

public interface EmailService {

	String sendEmailAuth(String email);
	void sendEmail(String to, String subject, String text) throws MessagingException;
	boolean verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest);
}
