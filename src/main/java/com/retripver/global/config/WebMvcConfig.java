package com.retripver.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.retripver.user.interceptor.LoginInterceptor;

@Configuration
@EnableWebMvc
class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
				.addPathPatterns("/user/follow/**");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //허용하려는 API 요청 경로
				.allowedOrigins("http://localhost:5173") //허용하려는 클라이언트 측 주소
				.allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH") //허용하려는 HTTP Method 설정
				.allowCredentials(true) //HttpOnly Cookie를 사용하기 위한 설정
				.exposedHeaders("Authorization") //클라이언트에서 해당 Header의 값을 확인할 수 있도록 설정
				.maxAge(1800); //Preflight Cache 설정
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/favicon.ico")
				.addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/js/**")
		        .addResourceLocations("/js/");
		registry.addResourceHandler("/css/**")
		        .addResourceLocations("/css/");
		registry.addResourceHandler("/images/**")
		        .addResourceLocations("/images/");
	}
}
