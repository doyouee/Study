package com.inzent.commonAPI.mapper;

import com.inzent.commonAPI.vo.CommonCodeGroupVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonCodeGroupMapper {
	//전체 공통코드그룹 조회
	List<Map<String,Object>> inquiry();
	//공통코드그룹 ID 중복 조회
	int idDuplication(@Param("code_group_id") String code_group_id);

	int nameDuplication(@Param("code_group_name") String code_group_name );
	//공통코드그룹 NAME 중복 등록
	void registration(@Param("group") CommonCodeGroupVO commonCodeGroupVO);

	//공통코드그룹 수정 (code_id 기준)
	void update(@Param("group") CommonCodeGroupVO commonCodeGroupVO);

	//공통코드그룹 삭제 (code_id 기준)
	int delete(@Param("group") CommonCodeGroupVO commonCodeGroupVO);

}
