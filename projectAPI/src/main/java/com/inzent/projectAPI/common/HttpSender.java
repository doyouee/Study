package com.inzent.projectAPI.common;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpSender {

    @Value("${API_KEY}")
    public static  String API_KEY;

    @Value("${API_KEY}")
    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String sndHTTP(String url, String type, String parameters, Map<String, Object> headerMap) {
        String result = "";
        URL urlObj = null;

        try {
            if("GET".equalsIgnoreCase(type)){
                if(parameters == null || parameters.isEmpty()){
                    urlObj = new URL(url);
                }else{
                    urlObj = new URL(url+"?"+parameters);
                }
            }else{
                urlObj = new URL(url);
            }

            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod(type.toUpperCase()); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setConnectTimeout(15000);
            // 인터샙터에서 셋팅한 headerMap을 for문으로 읽어서 setRequestProperty에 셋팅
            for (String key : headerMap.keySet()){
                conn.setRequestProperty(key, (String) headerMap.get(key));
            }
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true


            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                sb.append(line);
            }
            result = sb.toString();


        } catch (Exception e) {
            StringWriter errsw = new StringWriter();
            e.printStackTrace(new PrintWriter(errsw));
        }

        return result;
    }
}