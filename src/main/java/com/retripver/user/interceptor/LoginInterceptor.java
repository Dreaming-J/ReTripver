package com.retripver.user.interceptor;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import com.retripver.user.exception.UserErrorCode;
import com.retripver.user.exception.UserErrorResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("loginUser") == null) {
			response.setStatus(UserErrorResponse.of(UserErrorCode.NOT_FOUND_LOGIN_USER).getHttpStatus());
			response.setContentType("application/json");
			response.getWriter().write("{\"message\": \"Unauthorized - Please login first.\"}");

			return false;
		}

		return true;
	}

}
