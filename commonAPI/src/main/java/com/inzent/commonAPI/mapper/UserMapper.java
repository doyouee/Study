package com.inzent.commonAPI.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	
	String loginConfirm(Map<String, Object> loginInfo);

}
