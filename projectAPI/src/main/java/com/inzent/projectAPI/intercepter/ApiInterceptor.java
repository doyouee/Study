package com.inzent.projectAPI.intercepter;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import  com.inzent.projectAPI.common.HttpSender;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



class ApiInterceptor extends InterceptorRegistry implements HandlerInterceptor {


    static List<Map<String, Object>> APILIST = new ArrayList<>();
    private String expiredate;
    private String api_url ;
    private String api_key ;
    private  HttpSender httpSender = new HttpSender();

    public ApiInterceptor(String expiredate, String api_url, String api_key) {
        this.expiredate = expiredate;
        this.api_url = api_url;
        this.api_key = api_key;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
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
        if (APILIST.isEmpty() && hours >= 24) {
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("API_KEY",api_key);
            headerMap.put("USER_ID",request.getHeader("USER_ID"));
            headerMap.put("TOKEN_VALUE",request.getHeader("TOKEN_VALUE"));
            // 여기서 key, value 셋팅
            String sndResult =  httpSender.sndHTTP(api_url+"/apiInfo", "get",null, headerMap);
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
            if(o.get("API_KEY").equals(api_key)){
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