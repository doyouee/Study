package com.inzent.commonAPI.controller;

import com.inzent.commonAPI.service.CommonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommonCodeController {
	@Autowired
	private CommonCodeService commonCodeService;
	@GetMapping("/commonCode")
	public String inquiry() {
		// 공통코드 조회

		return 	commonCodeService.inquiry();
	}
	
	@PostMapping("/commonCode")
	public String registration() {
		// 공통코드 등록
		return "registration";
	}
	
	@PutMapping("/commonCode")
	public String update() {
		// 공통코드 수정
		return "update";
	}
	
	@DeleteMapping("/commonCode")
	public String delete() {
		// 공통코드 삭제
		return "delete";
	}
}
