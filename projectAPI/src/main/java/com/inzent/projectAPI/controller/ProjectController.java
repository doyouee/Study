package com.inzent.projectAPI.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inzent.projectAPI.service.ProjectService;
import com.inzent.projectAPI.vo.ProjectVO;

@RestController
public class ProjectController {
	
	 private ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }
	
	@GetMapping("/project")
	public String inquiry() {
		// 포로젝트 조회
		return "inquiry";
	}
	
	@PostMapping("/project")
	public String registration() {
		// 프로젝트 등록
		ProjectVO projectVO = new ProjectVO("id");
		projectService.addProject(projectVO);
		
		return "registration";
	}
	
	@PutMapping("/project")
	public String update() {
		// 프로젝트 수정
		return "update";
	}
	
	@DeleteMapping("/project")
	public String delete() {
		// 프로젝트 삭제
		return "delete";
	}
	
	@PutMapping("/projectUser")
	public String projectUserUpdate() {
		// 프로젝트 사용자 수정
		return "projectUserUpdate";
	}
	
	@PutMapping("/projectInspection")
	public String projectInspectionUpdate() {
		// 프로젝트 검수상태 수정
		return "projectInspectionUpdate";
	}
}
