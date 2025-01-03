package com.retripver.user.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import com.retripver.auth.dto.LoginResponse;
import com.retripver.auth.exception.AuthErrorCode;
import com.retripver.auth.exception.AuthErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		if ("GET".equals(request.getMethod())) return true;
		
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		
		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");

		if (session == null || loginUser == null) {
			AuthErrorResponse userErrorResponse = AuthErrorResponse.of(AuthErrorCode.NOT_FOUND_USER);
			String bodyMessage = "{\"message\": \"" + userErrorResponse.getMessage() + "\"}";
			
			response.setStatus(userErrorResponse.getHttpStatus());
			response.setContentType("application/json");
			response.getWriter().write(bodyMessage);

	
//			response.sendRedirect(request.getContextPath() + "/user/login?redirectURL=" + requestURI);
			
			return false;
		}

		return true;
	}

}
