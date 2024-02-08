package com.inzent.commonAPI.controller;

import com.inzent.commonAPI.service.CommonCodeGroupService;
import com.inzent.commonAPI.vo.CommonCodeGroupVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.inzent.commonAPI.common.HttpSender;

@RestController
public class CommonCodeGroupController {
	@Autowired
	private CommonCodeGroupService commonCodeGroupService;

	@GetMapping("/commonCodeGroup")
	public String inquiry() {
		// 공통코드그룹 조회
		return commonCodeGroupService.inquiry();
	}
	
	@PostMapping("/commonCodeGroup")
	public String registration(@RequestBody CommonCodeGroupVO commonCodeGroupVO) {
		// 공통코드그룹 등록
		int idDup = commonCodeGroupService.idDuplication(commonCodeGroupVO.getCode_group_id());
		int nameDup = commonCodeGroupService.nameDuplication(commonCodeGroupVO.getCode_group_name());

		if(idDup>0 && nameDup>0){
			return "all_Duplication";
		}else if(idDup>0){
			return "id_Duplication";
		}else if (nameDup>0) {
			return "name_Duplication";
		} else {
			commonCodeGroupService.registration(commonCodeGroupVO );
			return "registration";
		}

	}
	
	@PutMapping("/commonCodeGroup")
	public String update(@RequestBody CommonCodeGroupVO commonCodeGroupVO) {
		// 공통코드그룹 수정
		commonCodeGroupService.update(commonCodeGroupVO);
		return "update";
	}
	
	@DeleteMapping("/commonCodeGroup")
	public String delete(@RequestBody CommonCodeGroupVO commonCodeGroupVO) {
		// 공통코드그룹 삭제
		int result = commonCodeGroupService.delete(commonCodeGroupVO);
		if(result> 0){
			return "delete";
		}else{
			return "사용중인 그룹은 삭제할 수 없습니다.";
		}


	}
}
