package com.inzent.commonAPI.mapper;


import com.inzent.commonAPI.vo.CommonCodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonCodeMapper {
	List<Map<String,Object>> inquiry();


	void registration(@Param("code") CommonCodeVO commonCodeVO);
}
