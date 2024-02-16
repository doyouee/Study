package com.inzent.commonAPI.controller;

import com.inzent.commonAPI.service.CommonService;
import com.inzent.commonAPI.vo.ApiInfoVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonController {
	@Autowired
	private CommonService commonService;

	@Value("${API_KEY}")
	private String API_KEY ;
    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }


    public String menu() {
		// 메뉴 조회
		return "menu";
	}
	
	public String authority() {
		// 권한 조회
		return "authority";
	}
	

	@GetMapping("/apiInfo")
	public String getApiInfo() {
		// Api Key, url 정보 조회
		if("TRUE".equalsIgnoreCase(commonService.apiExistence(API_KEY))){
			return commonService.apiInfo();
		}else{
			return "";
		}

	}






}
