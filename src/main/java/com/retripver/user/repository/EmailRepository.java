package com.retripver.user.repository;

import com.retripver.user.dto.EmailCodeRequest;

public interface EmailRepository {

	void sendEmailAuth(EmailCodeRequest emailCodeRequest);

}
