package com.retripver.user.service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final String EMAIL_AUTH_SUBJECT = "[ReTripver] 이메일 인증";
	private final String EMAIL_AUTH_PASSWORD = "\nReTripver 회원가입 이메일 인증\n\n"
								   	   + "회원가입을 위해 등록한 이메일 주소가 올바른지 확인하기 위한 인증번호입니다.\n"
									   + "아래의 인증번호를 통해 이메일 인증을 완료해 주세요.\n\n";
	
	private final JavaMailSender mailSender;
	
	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public String sendEmailAuth(String email) {
		try {
			sendEmail(email, EMAIL_AUTH_SUBJECT, EMAIL_AUTH_PASSWORD);
			
			return "이메일로 인증 코드를 전송했습니다.";
		} catch (MessagingException e) {
			return "이메일 전송에 실패했습니다. 다시 시도해주세요.";
		}
	}
	
	public void sendEmail(String to, String subject, String text) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		
		helper.setTo(to); // 인증 메일을 보낼 사용자 메일 주소
		helper.setSubject(subject); // 이메일 제목
		helper.setText(text, true); // 이메일 내용
		
		mailSender.send(message);
	}
	
	 private JavaMailSender createMailSender() {
	        JavaMailSenderImpl sender = new JavaMailSenderImpl();
	        sender.setHost("smtp.gmail.com");
	        sender.setPort(587);  // 587 포트는 STARTTLS를 사용하기 위한 포트입니다.
	        sender.setUsername("your-email@gmail.com");  // 본인의 이메일로 설정
	        sender.setPassword("your-email-password");  // 본인의 이메일 비밀번호 설정

	        Properties properties = new Properties();
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true"); // STARTTLS 활성화
	        properties.put("mail.smtp.starttls.required", "true"); // STARTTLS 사용 강제

	        sender.setJavaMailProperties(properties);
	        return sender;
	    }

}
