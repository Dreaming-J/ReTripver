package com.retripver.user.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.retripver.user.dto.LoginResponse;
import com.retripver.user.exception.UserErrorCode;
import com.retripver.user.exception.UserErrorResponse;

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
			response.setStatus(UserErrorResponse.of(UserErrorCode.NOT_FOUND_LOGIN_USER).getHttpStatus());
			response.setContentType("application/json");
			response.getWriter().write("{\"message\": \"Unauthorized - Please login first.\"}");

//			response.sendRedirect(request.getContextPath() + "/user/login?redirectURL=" + requestURI);
			
			return false;
		}

		return true;
	}

}
