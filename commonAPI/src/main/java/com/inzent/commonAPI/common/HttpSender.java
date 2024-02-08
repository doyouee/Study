package com.inzent.commonAPI.common;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpSender {

    /* API_KEY 가 NULL 로 나오지 않기 위해서는
       HttpSender 선언시
       	@Autowired
	    private HttpSender httpSender ;
	    와 같이 선언해야 한다.
     */
    @Value("${API_KEY}")
    private String API_KEY;

    public String sndHTTP(String url, String type, String parameters) {
        String result = "";
        URL urlObj = null;

        try {
            if("GET".equalsIgnoreCase(type)){
                if(parameters.isEmpty()){
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
            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true
            conn.setRequestProperty("API_KEY",API_KEY);

            // post reqest 보내기
//            if ("POST".equalsIgnoreCase(type)){
//                DataOutputStream send = new DataOutputStream(conn.getOutputStream());
//                send.writeBytes(parameters);
//                send.flush();
//                send.close();
//
//            }
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
