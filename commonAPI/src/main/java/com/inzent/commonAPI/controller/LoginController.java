package com.inzent.commonAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.commonAPI.common.Json;
import com.inzent.commonAPI.service.MailService;
import com.inzent.commonAPI.service.UserService;

@RestController
public class LoginController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public String login(@RequestBody String loginInfo) {
		return userService.login(loginInfo);
	}
	
	@DeleteMapping("/logout")
	public boolean logout(@RequestHeader(value="TOKEN_VALUE") String tokenValue) {
		return userService.logout(tokenValue);
	}
}
