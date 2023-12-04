package com.clients.servlet;

import java.net.URI;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServletGetClient {
	public static void main (String[] args) {
		try {
			URI uri = new URI("http://localhost:8080/servletTest?param=1");
			
			CloseableHttpClient client = HttpClientBuilder.create().disableContentCompression().build();
			
			HttpGet httpGet = new HttpGet(uri);

			httpGet.setHeader("Content-Type", "application/json");
			
			CloseableHttpResponse response = client.execute(httpGet);
			
			String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			
			ObjectMapper mapper = new ObjectMapper();
			
			if(response.getStatusLine().getStatusCode() == HttpServletResponse.SC_OK) {
				System.out.println(" ● 성공 >> " + content);
			} else {
				System.out.println(" ● 실패 >> " + content);
			}
		} catch(Exception e) {
			System.out.println("● ● ● ● ● ● ● ● ● ● ● ● ● 에 러 에 러  ● ● ● ● ● ● ● ● ● ● ● ● ● ●");
			e.printStackTrace();
		}
		
	}
}
