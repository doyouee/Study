package com.inzent.projectAPI.intercepter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import  com.inzent.projectAPI.common.HttpSender;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ApiInterceptor2 extends InterceptorRegistry implements HandlerInterceptor {

    private static List<Map<String, Object>> APILIST = new ArrayList<>();
    private String expiredate;
    private String api_url ;
    private  HttpSender httpSender = new HttpSender();

    public ApiInterceptor2(String expiredate, String api_url) {
        this.expiredate = expiredate;
        this.api_url = api_url;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, Object> headerValue = new HashMap<>();
        headerValue.put("USER_ID", request.getHeader("USER_ID"));
        headerValue.put("TOKEN_VALUE", request.getHeader("TOKEN_VALUE"));
        headerValue.put("API_KEY", request.getHeader("API_KEY"));

        //유효시간(24시간)지났는지 현재시간과 비교하여 체크
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date now = new Date();
        Date date = null;
        try {
            date = sdf.parse(expiredate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        long difference = now.getTime() - date.getTime();
        int hours = (int) (difference / (1000 * 60 * 60));
        if (APILIST.isEmpty() || hours >= 24) {
            String param = "api_key="+headerValue.get("API_KEY");
            String url = api_url + "/apiInfo" + "?" + param;
            String sndResult =  httpSender.sndHTTP(url, "GET", "", headerValue);
            if(sndResult.isEmpty()){
                return false;
            }else{
                expiredate = sdf.format(now);
                ObjectMapper mapper = new ObjectMapper();
                APILIST =new ObjectMapper().readValue(sndResult, new TypeReference<List<Map<String, Object>>>(){});
            }
        }
        String exist = "";
        for (Map<String, Object> o : APILIST){
            if(o.get("API_KEY").equals(headerValue.get("API_KEY"))){
                exist ="true";
            }
        }
        if ("TRUE".equalsIgnoreCase(exist)){
            return true;
        }else {
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

