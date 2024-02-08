package com.inzent.commonAPI.controller;

import com.inzent.commonAPI.service.CommonCodeService;
import com.inzent.commonAPI.vo.CommonCodeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommonCodeController {
	@Autowired
	private CommonCodeService commonCodeService;

	@GetMapping("/commonCode")
	public String inquiry(@RequestParam String type , String code_id) {
//		 공통코드 조회
//		 # type  지정
//		 # all - 공통코드 그룹을 포함한 전체 공통코드 조회
//		 # only - 전체 공통코드 조회
//		 # code_id - 하나의 공통코드ID와 동일한 공통 그룹을 가지고 있는 모든 리스트 출력
//		 # top - 상위공통코드만 조회
		if("ALL".equalsIgnoreCase(type)){
			return 	commonCodeService.groupInquiry();
		}else if("ONLY".equalsIgnoreCase(type)){
			return 	commonCodeService.inquiry();
		}else if("CODE_ID".equalsIgnoreCase(type)){
			return commonCodeService.codeInquiry(code_id);
		}else if("TOP".equalsIgnoreCase(type)){
			return commonCodeService.topInquiry();
		}
		return "type을 지정해주세요";
	}
	
	@PostMapping("/commonCode")
	public String registration(@RequestBody  CommonCodeVO commonCodeVO) {
		// 공통코드 등록
		commonCodeService.registration(commonCodeVO );
		return "registration";
	}
	
	@PutMapping("/commonCode")
	public String update(@RequestBody CommonCodeVO commonCodeVO) {
		// 공통코드 수정
		commonCodeService.update(commonCodeVO);
		return "update";
	}
	
	@DeleteMapping("/commonCode")
	public String delete(@RequestBody CommonCodeVO commonCodeVO) {
		// 공통코드 삭제
		int result = commonCodeService.delete(commonCodeVO);
		if(result> 0){
			return "delete";
		}else{
			return "사용중인 상위공통코드는 삭제할 수 없습니다.";
		}
	}
}
