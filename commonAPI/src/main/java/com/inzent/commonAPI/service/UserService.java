package com.inzent.commonAPI.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inzent.commonAPI.common.Json;
import com.inzent.commonAPI.mapper.UserMapper;
import com.inzent.commonAPI.token.JwtTokenProvider;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public String login(String loginInfo) {
		String tokenValue = "";
		try {
			String userId = userMapper.loginConfirm(Json.jsonToMap(loginInfo));
			if(userId == null || userId.isEmpty()) { // login 정보가 없을 경우 빈값 return
				return tokenValue;
			}else { // login 정보가 존재할 경우 token 값 return
				tokenValue = jwtTokenProvider.createToken(userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tokenValue;
	}

	public boolean logout(String tokenValue) {
		try {
			jwtTokenProvider.removeToken(tokenValue);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
