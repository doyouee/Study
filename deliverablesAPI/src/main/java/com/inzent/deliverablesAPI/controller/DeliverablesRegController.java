package com.inzent.deliverablesAPI.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliverablesRegController {
	
	@GetMapping("/deliverablesReg")
	public String inquiry() {
		// 등록된 산출물 조회
		return "inquiry";
	}
	
	@PostMapping("/deliverablesReg")
	public String registration() {
		// 산출물 등록
		return "registration";
	}
	@PostMapping("/deliverablesReg")
	public String update() {
		// 산출물 수정
		return "registration";
	}
	
	@DeleteMapping("/deliverablesReg")
	public String delete() {
		// 산출물 삭제
		return "delete";
	}
}
