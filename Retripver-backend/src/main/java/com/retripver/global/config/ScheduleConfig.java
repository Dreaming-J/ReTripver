package com.retripver.global.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.retripver.auth.repository.AuthRepository;

@Configuration
@EnableScheduling // 일정 주기마다 동작할 수 있도록 명시.
public class ScheduleConfig {

    private final AuthRepository authRepository;

    @Autowired
    public ScheduleConfig(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

//    @Scheduled(cron = "0 * * * * *") // 1분 마다
    @Scheduled(cron = "0 0 4 * * *") // 매일 새벽 4시
    public void deleteExpiredTokens() {
    	authRepository.deleteExpiredTokens();
    }
}
