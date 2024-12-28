package com.retripver.global.config;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class OpenCVConfig {
    
	@PostConstruct
    public void init() {
        nu.pattern.OpenCV.loadLocally();
    }
}
