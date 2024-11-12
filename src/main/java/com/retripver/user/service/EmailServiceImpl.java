package com.retripver.user.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.retripver.user.dto.EmailAuthVerifyRequest;
import com.retripver.user.dto.EmailCodeRequest;
import com.retripver.user.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final String EMAIL_AUTH_SUBJECT = "[ReTripver] 이메일 인증";
	private final String EMAIL_AUTH_PASSWORD = "\nReTripver 회원가입 이메일 인증\n\n"
								   	   + "회원가입을 위해 등록한 이메일 주소가 올바른지 확인하기 위한 인증번호입니다.\n"
									   + "아래의 인증번호를 통해 이메일 인증을 완료해 주세요.\n\n"
								   	   + "인증 코드 \n";
	
	private final EmailRepository emailRepository;
	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(EmailRepository emailRepository, JavaMailSender mailSender) {
		this.emailRepository = emailRepository;
		this.mailSender = mailSender;
	}

	@Override
	public String sendEmailAuth(String email) {
		try {
			String code = generateAuthCode();
			
			emailRepository.sendEmailAuth(new EmailCodeRequest(email, code));
			
//			sendEmail(email, EMAIL_AUTH_SUBJECT, EMAIL_AUTH_PASSWORD + code);
			
			return "이메일로 인증 코드를 전송했습니다.";
		} catch (Exception e) {
			return "이메일 전송에 실패했습니다. 다시 시도해주세요.";
		}
	}
	
	@Override
	public boolean verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp.getTime());
		cal.add(Calendar.SECOND, 60 * 3);
		
		Timestamp later = new Timestamp(cal.getTime().getTime());
		
		
		System.out.println(timestamp);
		System.out.println(later);
		
		
		return false;
	}
	
	private void sendEmail(String to, String subject, String text) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();

		message.setFrom("ReTripver@gmail.com"); // 인증 메일을 보낼 사용자 메일 주소
		message.setRecipients(MimeMessage.RecipientType.TO, to);
		message.setSubject(subject); // 이메일 제목
		message.setText(text, "UTF-8", "html"); // 이메일 내용
		
		mailSender.send(message);
	}
	
	private String generateAuthCode() {
		Random random = new Random();
		
		int code = 100000 + random.nextInt(900000);
		
		return String.valueOf(code);
	}

}
