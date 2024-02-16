package com.inzent.projectAPI.controller;

import com.inzent.projectAPI.common.ResponseMessage;
import com.inzent.projectAPI.vo.ProjectInspectionVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import com.inzent.projectAPI.service.ProjectService;

@RestController
public class ProjectController {
	@Autowired
	private final ProjectService projectService;
	ResponseMessage responseMessage = new ResponseMessage();
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

	@PostMapping("/project")
	public String registration() {
		// 프로젝트 등록
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

	/* 프로젝트 검색 조회 
	* */
	@GetMapping("/project")
	public ResponseEntity<String> searchProject(@RequestParam(value="project_id", required = false) String project_id, @RequestParam(value="project_name", required = false) String project_name, @RequestParam(value="team_id", required = false) String team_id, @RequestParam(value="inspection_status", required = false) Integer inspection_status) {
		System.out.println("controller 결과 :: " + projectService.searchProject(project_id, project_name, team_id, inspection_status));
		return new ResponseEntity<>(responseMessage.setMessage("SUCCESS", projectService.searchProject(project_id, project_name, team_id, inspection_status)), HttpStatus.OK);
	}

	/* 프로젝트 검수 상태 수정 */
	@PutMapping("/inspection")
	public String projectInspectionUpdate(HttpServletRequest request, @RequestHeader("USER_ID") String userId, @RequestBody ProjectInspectionVO projectInspectionVO) {
		return responseMessage.setMessage("SUCCESS", projectService.editProjectInspection(request, userId, projectInspectionVO));
	}

	/* 산출물 zip 다운로드 */
	@GetMapping("/deliverablesDownload")
	public String projectDeliverablesDownload(HttpServletRequest request, @RequestParam String projectId) {
		return responseMessage.setMessage("SUCCESS", projectService.downloadDeliverables(request, projectId));
	}
}
