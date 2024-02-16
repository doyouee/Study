package com.inzent.commonAPI.intercepter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import  com.inzent.commonAPI.http.HttpSender;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



class ApiInterceptor extends InterceptorRegistry implements HandlerInterceptor {


    static ArrayList<HashMap<String, String>> APILIST = new ArrayList<>();
    @Autowired
    private String expiredate;
    private String api_url ;
    private HttpSender httpSender;
    public ApiInterceptor(String expiredate, String api_url) {

        this.expiredate = expiredate;
        this.api_url = api_url;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("===============================================");
        System.out.println("==================== BEGIN ====================");



        String api_key = request.getHeader("API_KEY");



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

            String sndResult =  httpSender.sndHTTP(api_url, "get");
            if(sndResult.isEmpty()){
                return false;
            }else{
                expiredate = sdf.format(now);
                System.out.println(expiredate);
                //APILIST = (List<Object>) sndResult;
                ObjectMapper mapper = new ObjectMapper();

                APILIST = mapper.readValue(sndResult,
                        new TypeReference<ArrayList<HashMap<String, String>>>() {});


            }

        }

        for (HashMap<String, String> o : APILIST){
            if(o.get("API_KEY").equals(api_key)){
                return true;
            }else {
                return false;
            }

        }


        return false;
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
