package com.inzent.deliverablesAPI.controller;

import com.inzent.deliverablesAPI.service.DeliverablesListService;
import com.inzent.deliverablesAPI.vo.DeliverablesListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class DeliverablesListController {

	@Autowired
	private DeliverablesListService deliverablesListService;

	// 산출물 목록 조회 (GET)
	@RequestMapping(value = "/deliverablesList", method=RequestMethod.GET)
	public List<DeliverablesListVO> getList(Model model) throws Exception{
		List<DeliverablesListVO> list = deliverablesListService.getList();
		model.addAttribute("deliverlist", list);
		return list;
	}

	// 산출물 목록의 디렉터리 또는 파일 등록 (POST)
	@RequestMapping(value = "/deliverablesList", method = RequestMethod.POST)
	public String postList(@RequestBody DeliverablesListVO deliverablesListVO){
		deliverablesListService.postList(deliverablesListVO);
		return "Success";
	}

	// 산출물 목록의 디렉토리 또는 파일 수정 (PUT)
	@RequestMapping(value = "/deliverablesList", method = RequestMethod.PUT)
	public String putList(@RequestBody DeliverablesListVO deliverablesListVO){
		deliverablesListService.putList(deliverablesListVO);
		return "Success";
	}

	// 산출물 목록의 디렉토리 또는 파일 삭제 (DELETE)
	@RequestMapping(value = "/deliverablesList", method = RequestMethod.DELETE)
	public String deleteList(String id){
		deliverablesListService.deleteList(id);
		return "Success";
	}
}
