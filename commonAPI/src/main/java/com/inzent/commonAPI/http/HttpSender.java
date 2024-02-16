package com.inzent.commonAPI.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpSender {


    public String sndHTTP(String url, String type) {
        String result = "";
        URL urlObj = null;
        try {
            urlObj = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        try {

            System.out.println(urlObj);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            conn.setRequestMethod("GET"); // http 메서드
            conn.setRequestProperty("Content-Type", "application/json"); // header Content-Type 정보
            conn.setConnectTimeout(15000);

            conn.setDoOutput(true); // 서버로부터 받는 값이 있다면 true



            // 서버로부터 데이터 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = br.readLine()) != null) { // 읽을 수 있을 때 까지 반복
                sb.append(line);
            }
            int responseCode = conn.getResponseCode();
            result = sb.toString();


        } catch (Exception e) {
            StringWriter errsw = new StringWriter();
            e.printStackTrace(new PrintWriter(errsw));

        }

        return result;
    }
}
