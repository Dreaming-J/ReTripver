package com.retripver.auth.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.retripver.auth.dto.EmailAuthResponse;
import com.retripver.auth.dto.EmailCodeRequest;
import com.retripver.auth.mapper.EmailMapper;

@Repository
public class EmailRepositoryImpl implements EmailRepository {
	
	private final EmailMapper emailMapper;
	
	@Autowired
	public EmailRepositoryImpl (EmailMapper emailMapper) {
		this.emailMapper = emailMapper;
	}

	@Override
	public void sendEmailAuth(EmailCodeRequest emailCodeRequest) {
		int isExistEmail = emailMapper.selectCountByEmail(emailCodeRequest.getEmail());
		if (isExistEmail != 0) {
			emailMapper.deleteByEmail(emailCodeRequest.getEmail());
		}

		emailMapper.insert(emailCodeRequest);
	}

	@Override
	public EmailAuthResponse verifyEmailAuth(String email) {
		return emailMapper.selectById(email);		
	}

}
