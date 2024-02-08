package com.inzent.commonAPI.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.inzent.commonAPI.token.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class TokenInterceptor extends InterceptorRegistry implements HandlerInterceptor {
	
	private JwtTokenProvider jwtTokenProvider;
	
	public TokenInterceptor(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
			if(jwtTokenProvider.validateToken(request.getHeader("TOKEN_VALUE"))) {
				return true;
			}else {
				response.setStatus(401);
				return false;
			}
		}catch (Exception e) {
			e.printStackTrace();
			response.setStatus(401);
			return false;
		}
    }
}
