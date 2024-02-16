package com.inzent.projectAPI.mapper;

import com.inzent.projectAPI.vo.ProjectInspectionVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProjectMapper {

	/* 프로젝트 검수 상태 수정 */
	void editInspection(ProjectInspectionVO projectInspectionVO);

	/* 산출물 zip 다운로드 */
	String getProjectName(@Param("projectId") String projectId);

	List<Map<String, Object>> searchProject(@Param("project_id") @Nullable String project_id, @Param("project_name") @Nullable String project_name, @Param("team_id") @Nullable String team_id, @Param("inspection_status") @Nullable Integer inspection_status);
}
