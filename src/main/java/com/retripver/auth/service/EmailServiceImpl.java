package com.retripver.auth.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.retripver.auth.dto.EmailAuthResponse;
import com.retripver.auth.dto.EmailAuthVerifyRequest;
import com.retripver.auth.dto.EmailAuthVerifyResponse;
import com.retripver.auth.dto.EmailCodeRequest;
import com.retripver.auth.repository.EmailRepository;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
	
	private final String EMAIL_AUTH_SUBJECT = "[ReTripver] 이메일 인증";
	private final String EMAIL_AUTH_PASSWORD = "<h3>ReTripver 회원가입 이메일 인증</h3>"
								   	   + "회원가입을 위해 등록한 이메일 주소가 올바른지 확인하기 위한 인증번호입니다.<br>"
									   + "아래의 인증번호를 통해 이메일 인증을 완료해 주세요.<br>"
								   	   + "인증 코드<br>";
	
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
			Timestamp completeTime = getAuthCompleteTime();
			emailRepository.sendEmailAuth(new EmailCodeRequest(email, code, completeTime));

			sendEmail(email, EMAIL_AUTH_SUBJECT, EMAIL_AUTH_PASSWORD + code);
			
			return "이메일로 인증 코드를 전송했습니다.";
		} catch (Exception e) {
			return "이메일 전송에 실패했습니다. 다시 시도해주세요.";
		}
	}
	
	@Override
	public EmailAuthVerifyResponse verifyEmailAuth(EmailAuthVerifyRequest emailAuthVerifyRequest) {
		EmailAuthResponse emailAuthResponse = emailRepository.verifyEmailAuth(emailAuthVerifyRequest.getEmail());

		if (isCompleteAuthTime(emailAuthResponse.getCompleteTime())) {// 시간이 만료되었다면
			return new EmailAuthVerifyResponse(false, "인증 시간이 지났습니다. 이메일 인증을 다시 시도해주세요.");
		}

		String generatedCode = emailAuthResponse.getCode();
		String requestCode = emailAuthVerifyRequest.getCode();

		if (!generatedCode.equals(requestCode)) {
			return new EmailAuthVerifyResponse(false, "인증 코드가 다릅니다. 다시 입력해주세요.");
		}

		return new EmailAuthVerifyResponse(true, "인증이 확인되었습니다.");
	}
	
	@Async
	private CompletableFuture<Void> sendEmail(String to, String subject, String text) throws MessagingException {
		return CompletableFuture.runAsync(() -> {
			try {
				MimeMessage message = mailSender.createMimeMessage();

				message.setFrom("ReTripver@gmail.com"); // 인증 메일을 보낼 사용자 메일 주소
				message.setRecipients(MimeMessage.RecipientType.TO, to);
				message.setSubject(subject); // 이메일 제목
				message.setText(text, "UTF-8", "html"); // 이메일 내용

				mailSender.send(message);
				System.out.println("메일 전송");
			} catch (MessagingException e) {
				e.getStackTrace();
			}
		});
	}
	
	private String generateAuthCode() {
		Random random = new Random();
		
		int code = 100000 + random.nextInt(900000);
		
		return String.valueOf(code);
	}
	
	private Timestamp getAuthCompleteTime() {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(currentTime.getTime());
		cal.add(Calendar.SECOND, 60 * 3);
		
		Timestamp completeTime = new Timestamp(cal.getTime().getTime());
		
		return completeTime;
	}
	
	private boolean isCompleteAuthTime(Timestamp completeTime) {
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		
		Instant currentTimeInstat = currentTime.toInstant();
		Instant complateTimeInstat = completeTime.toInstant();
		
		if (currentTimeInstat.isAfter(complateTimeInstat)) {
			return true;
		}

		return false;
	}

}
