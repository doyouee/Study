package com.inzent.deliverablesAPI.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliverablesListController {
	
	@GetMapping("/deliverablesList")
	public String inquiry() {
		// 산출물 목록 조회
		return "inquiry";
	}
	
	@PostMapping("/deliverablesList")
	public String registration() {
		// 산출물 목록 등록
		return "registration";
	}
	
	@PutMapping("/deliverablesList")
	public String update() {
		// 산출물 목록 수정
		return "update";
	}
	
	@DeleteMapping("/deliverablesList")
	public String delete() {
		// 산출물 목록 삭제
		return "delete";
	}
}
