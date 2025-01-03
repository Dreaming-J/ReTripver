package com.retripver.auth.repository;

import com.retripver.auth.dto.EmailAuthResponse;
import com.retripver.auth.dto.EmailCodeRequest;

public interface EmailRepository {

	void sendEmailAuth(EmailCodeRequest emailCodeRequest);

	EmailAuthResponse verifyEmailAuth(String email);

}
