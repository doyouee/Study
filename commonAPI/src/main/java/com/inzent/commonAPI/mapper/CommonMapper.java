package com.inzent.commonAPI.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {
	List<Map<String, Object>> apiInfo();

	String apiExistence(@Param("api_key") String api_key);
}
