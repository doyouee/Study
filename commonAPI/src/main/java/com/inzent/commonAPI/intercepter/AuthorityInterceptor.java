package com.inzent.commonAPI.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.inzent.commonAPI.service.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthorityInterceptor extends InterceptorRegistry implements HandlerInterceptor {
	
	private CommonService commonService;
	
	public AuthorityInterceptor(CommonService commonService){
		this.commonService = commonService;
	}

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		try {
	        if(commonService.tokenAuthorityConfirm(request.getHeader("USER_ID"), request.getRequestURI().replace("/", ""), request.getMethod())) {
				return true;
			}else {
				response.setStatus(403);
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(403);
        	return false;
		}
    }
}
