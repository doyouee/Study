package com.inzent.projectAPI.intercepter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public  class WebMvcConfiguration  implements WebMvcConfigurer {

    @Value("${EXPIREDATE}")
    private String EXPIREDATE ;

    @Value("${API_URL}")
    private String API_URL ;

    @Value("${API_KEY}")
    private String API_KEY ;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry)  {
        /*
         * 1. api key 확인 (HEADER/API_KEY), 404 ERROR
         * 2. token 확인 (HEADER/TOKEN_VALUE), 401 ERROR
         * 3. 권한 확인 (HEADER/USER_ID), 403 ERROR
         */
        interceptorRegistry.addInterceptor(new ApiInterceptor(EXPIREDATE,API_URL,API_KEY)).addPathPatterns("/**").order(1);
        interceptorRegistry.addInterceptor(new ConfirmInterceptor2(API_URL)).addPathPatterns("/**").order(2);
    }


}
