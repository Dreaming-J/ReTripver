package com.retripver.user.repository;

import com.retripver.user.dto.EmailAuthResponse;
import com.retripver.user.dto.EmailAuthVerifyRequest;
import com.retripver.user.dto.EmailAuthVerifyResponse;
import com.retripver.user.dto.EmailCodeRequest;

public interface EmailRepository {

	void sendEmailAuth(EmailCodeRequest emailCodeRequest);

	EmailAuthResponse verifyEmailAuth(String email);

}
