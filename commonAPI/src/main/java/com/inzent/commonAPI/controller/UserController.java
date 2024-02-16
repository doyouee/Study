package com.inzent.commonAPI.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@GetMapping("/user")
	public String inquiry() {
		// 사용자 조회
		return "inquiry";
	}
	
	@PostMapping("/user")
	public String registration() {
		// 사용자 등록
		return "registration";
	}
	
	@PutMapping("/user")
	public String update() {
		// 사용자 수정
		// 비밀번호 초기화
		return "update";
	}
	
	@DeleteMapping("/user")
	public String delete() {
		// 사용자 삭제
		return "delete";
	}
}
