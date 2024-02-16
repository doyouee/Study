package com.inzent.commonAPI.intercepter;

import com.inzent.commonAPI.mapper.CommonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.inzent.commonAPI.intercepter.ApiInterceptor;
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {


    @Value("${EXPIREDATE}")
    private String EXPIREDATE ;

    @Value("${API_URL}")
    private String API_URL ;
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(new ApiInterceptor(EXPIREDATE,API_URL)).addPathPatterns("/**").excludePathPatterns("/apiInfo");
    }
}
