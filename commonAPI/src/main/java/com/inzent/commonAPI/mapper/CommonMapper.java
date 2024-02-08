package com.inzent.commonAPI.mapper;

import com.inzent.commonAPI.vo.MenuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommonMapper {

	//전체  API List 조회
	List<Map<String, Object>> apiInfo();
//	Map<String, List<String>> ProjectListView();
	
	// 메뉴 조회
	List<MenuVO> getMenu();
	
	//API_KEY 로 API_NAME 반환
	String searchApiId(@Param("api_key") String api_key);

	//특정 API_KEY가 존재하는지 확인 후 true/false 반환
	String apiExistence(@Param("api_key") String api_key);
	
	// 매핑URL의 권한 여부 확인
	String authorityConfirm(Map<String, Object> confirmInfo);

}
