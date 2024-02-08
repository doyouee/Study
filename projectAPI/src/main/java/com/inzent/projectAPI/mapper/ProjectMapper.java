package com.inzent.projectAPI.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.inzent.projectAPI.vo.ProjectVO;

@Mapper
public interface ProjectMapper {
	void registration(@Param("project") ProjectVO projectVO);
}
