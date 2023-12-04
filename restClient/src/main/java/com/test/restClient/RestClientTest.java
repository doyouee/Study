package com.test.restClient;

import java.net.URI;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RestClientTest {
	public static void main (String[] args) {
		try {
			URI uri = new URI("http://localhost:8080/test");
			
			CloseableHttpClient client = HttpClientBuilder.create().disableContentCompression().build();
			
			HttpPost httpPost = new HttpPost(uri);
			HashMap<String, Object> param = new HashMap<String, Object>();
			
			// 파라미터 세팅
			param.put("name", "홍길동");
			param.put("age", 110);
			param.put("school", "대학대학교");
			
			ObjectMapper paramMapper = new ObjectMapper();
			String json = paramMapper.writeValueAsString(param);
			
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setEntity(new StringEntity(json, "UTF-8")); // 한글 깨짐 방지
			
			// Post 호출
			CloseableHttpResponse response = client.execute(httpPost);
			
			// 응답 값 가져오기
			String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			// 응답 값 HashMap 변환
			ObjectMapper mapper = new ObjectMapper();
//			HashMap<String, Object> responseMap = mapper.readValue(content, HashMap.class);
			
			if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
				System.out.println(" ● 성공 >> " + content); //응답 성공
			} else {
				System.out.println(" ● 실패 >> " + content); // 응답 실패
			}
		} catch(Exception e) {
			System.out.println("● ● ● ● ● ● ● ● ● ● ● ● ● 에 러 에 러  ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
			e.printStackTrace();
		}
		
	}
}
