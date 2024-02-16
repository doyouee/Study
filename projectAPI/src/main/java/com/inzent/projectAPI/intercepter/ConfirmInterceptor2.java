package com.inzent.projectAPI.intercepter;

import com.inzent.projectAPI.common.HttpSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.*;

public class ConfirmInterceptor2 extends InterceptorRegistry implements HandlerInterceptor {

    private static List<Map<String, Object>> APILIST = new ArrayList<>();
    private String api_url ;
    private HttpSender httpSender = new HttpSender();

    public ConfirmInterceptor2(String api_url) {
        this.api_url = api_url;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Map<String, Object> headerValue = new HashMap<>();
            headerValue.put("USER_ID", request.getHeader("USER_ID"));
            headerValue.put("TOKEN_VALUE", request.getHeader("TOKEN_VALUE"));
            headerValue.put("API_KEY", request.getHeader("API_KEY"));
            headerValue.put("MAPPING_URL", request.getRequestURI());
            headerValue.put("METHOD", request.getMethod());

            String url = api_url + "/confirm";
            String sndResult = httpSender.sndHTTP(url, "GET", "", headerValue);

            if ("TRUE".equalsIgnoreCase(sndResult)){
                return true;
            }else {
                response.setStatus(401);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e : "+e);
            response.setStatus(401);
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
