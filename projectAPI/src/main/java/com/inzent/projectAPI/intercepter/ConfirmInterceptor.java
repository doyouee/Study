package com.inzent.projectAPI.intercepter;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inzent.projectAPI.common.HttpSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


class ConfirmInterceptor extends InterceptorRegistry implements HandlerInterceptor {


    private  HttpSender httpSender = new HttpSender();

    private String api_key;

    public ConfirmInterceptor(String apiUrl) {
        this.api_key = apiUrl;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("ConfirmInterceptor :: " );
        String url = "http://192.168.18.137:8081/confirm";
        URL urlObj = new URL(url);
        String type = "get";

        String responseData = "";
        BufferedReader br = null;
        StringBuffer sb = null;
        String returnData = "";

        try {
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod(type.toUpperCase()); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setConnectTimeout(15000);
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true
            conn.setRequestProperty("API_KEY",request.getHeader("API_KEY"));
            conn.setRequestProperty("USER_ID",request.getHeader("USER_ID"));
            conn.setRequestProperty("TOKEN_VALUE",request.getHeader("TOKEN_VALUE"));
            conn.setRequestProperty("MAPPING_URL",request.getRequestURI());
            conn.setRequestProperty("METHOD",request.getMethod());
            conn.connect();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            sb = new StringBuffer();
            while((responseData = br.readLine()) != null) {
                sb.append(responseData);
            }
            returnData = sb.toString();
            System.out.println("response ::: " + response.getStatus());
            System.out.println("returnData ::: " + returnData);

            return true;
        }catch (Exception e) {
            e.printStackTrace();
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