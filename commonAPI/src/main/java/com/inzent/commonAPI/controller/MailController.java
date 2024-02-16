package com.inzent.commonAPI.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
	
	public String sendMail() {
		// 메일 발송
		return "sendMail";
	}
	
	public String getMailInfo() {
		// 메일 정보 
		return "getMailInfo";
	}
}
