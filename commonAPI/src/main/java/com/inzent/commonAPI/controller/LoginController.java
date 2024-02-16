package com.inzent.commonAPI.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@PostMapping("/login")
	public String inquiry() {
		// 로그인
		return "inquiry";
	}
	
	@DeleteMapping("/logout")
	public String delete() {
		// 로그아웃
		return "delete";
	}
}
