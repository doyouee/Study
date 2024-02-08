package com.inzent.commonAPI.intercepter;

import com.inzent.commonAPI.mapper.CommonMapper;
import com.inzent.commonAPI.service.CommonService;
import com.inzent.commonAPI.token.JwtTokenProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public  class WebMvcConfiguration  implements WebMvcConfigurer {

    @Value("${EXPIREDATE}")
    private String EXPIREDATE ;

    @Autowired
    private CommonMapper commonMapper;
    
    @Autowired
    private CommonService commonService;
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry)  {
		/*
		 * 1. api key 확인 (HEADER/API_KEY), 404 ERROR
		 * 2. token 확인 (HEADER/TOKEN_VALUE), 401 ERROR
		 * 3. 권한 확인 (HEADER/USER_ID), 403 ERROR
		 */
        interceptorRegistry.addInterceptor(new ApiInterceptor(EXPIREDATE,commonMapper)).addPathPatterns("/**").order(1);
        interceptorRegistry.addInterceptor(new TokenInterceptor(jwtTokenProvider)).addPathPatterns("/**").excludePathPatterns("/login").order(2);
		interceptorRegistry.addInterceptor(new AuthorityInterceptor(commonService)).addPathPatterns("/**").excludePathPatterns("/login").order(3);
    }
}