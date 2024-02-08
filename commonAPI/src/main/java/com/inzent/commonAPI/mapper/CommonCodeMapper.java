package com.inzent.commonAPI.mapper;


import com.inzent.commonAPI.vo.CommonCodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonCodeMapper {
	//전체 공통코드 조회
	List<Map<String,Object>> inquiry();

	//공통코드 그룹을 포함한 전체 공통코드 조회
	List<Map<String,Object>> groupInquiry();

	//하나의 공통코드ID와 동일한 공통 그룹을 가지고 있는 모든 리스트 출력
	List<Map<String,Object>> codeInquiry(@Param("code_id") String code_id);

	//상위 공통코드 조회
	List<Map<String,Object>> topInquiry();

	//공통코드 등록
	void registration(@Param("code") CommonCodeVO commonCodeVO);

	//공통코드 수정 (code_id 기준)
	void update(@Param("code") CommonCodeVO commonCodeVO);

	//공통코드 삭제 (code_id 기준)
	int delete(@Param("code") CommonCodeVO commonCodeVO);
}
