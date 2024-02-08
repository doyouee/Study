package com.inzent.commonAPI.controller;

import com.inzent.commonAPI.service.CommonService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	@Autowired
	private CommonService commonService;

	@GetMapping("/apiInfo")
	public String getApiInfo(@RequestParam String api_key) {
		// Api Key, url 정보 조회
		if("TRUE".equalsIgnoreCase(commonService.apiExistence(api_key))){
			return commonService.apiInfo();
		}else{
			return "false";
		}
	}
	
	public String searchApiId(String api_key){
		// API_KEY 로 API_ID 조회
		return commonService.searchApiId( api_key);
	}
	
	@GetMapping("/confirm")
	public boolean tokenAuthorityConfirm(@RequestHeader(value="USER_ID") String userId, @RequestHeader(value="MAPPING_URL") String mappingUrl, 
			@RequestHeader(value="METHOD") String method, HttpServletResponse response) {
		// 다른 API에서 호출 할 경우 권한 체크
		if(commonService.tokenAuthorityConfirm(userId, mappingUrl, method)) {
			return true;
		}else {
			response.setStatus(403);
			return false;
		}
	}

}
