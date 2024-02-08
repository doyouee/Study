package com.inzent.commonAPI.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.commonAPI.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	private MailService mailService;
	
	@PostMapping("/sendMail")
	public String sendMail(@RequestHeader(value="API_KEY") String api_key, @RequestBody String sendInfo) {
		
		return mailService.sendMail(api_key, sendInfo);
	}
}
