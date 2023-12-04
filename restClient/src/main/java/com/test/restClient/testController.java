package com.test.restClient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
		
		@PostMapping("/")
		public Map<String, Object> testListener(@RequestBody testDto test) {
			Map<String, Object> result = new HashMap<String, Object>();
			if(test != null) {
				System.out.println(test.toString());
				result.put("status", "success!!!");
				result.put("value", test.toString());
			} else {
				result.put("status", "fail!!!");
			}
			return result;
			
		}

}
