package com.inzent.commonAPI.intercepter;

import com.inzent.commonAPI.mapper.CommonMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


class ApiInterceptor extends InterceptorRegistry implements HandlerInterceptor {

    static List<Map<String, Object>> APILIST = new ArrayList<>();
    private CommonMapper commonMapper;
    
    @Autowired
    private String expiredate;

    public ApiInterceptor(String expiredate, CommonMapper commonMapper) {
        this.expiredate = expiredate;
        this.commonMapper = commonMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
    	try {
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
                List<Map<String, Object>> sndResult = commonMapper.apiInfo();
                if(sndResult.isEmpty()){
                    return false;
                }else {
                    expiredate = sdf.format(now);
                    APILIST = sndResult;
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
            	response.setStatus(404);
                return false;
            }
    	} catch (Exception e) {
    		e.printStackTrace();
    		response.setStatus(404);
            return false;
		}
    }
}