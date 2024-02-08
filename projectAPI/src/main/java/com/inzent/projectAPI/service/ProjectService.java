package com.inzent.projectAPI.service;

import org.springframework.stereotype.Service;

import com.inzent.projectAPI.mapper.ProjectMapper;
import com.inzent.projectAPI.vo.ProjectVO;

@Service
public class ProjectService {
	private ProjectMapper projectMapper;

    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public void addProject(ProjectVO projectVO) {
    	projectMapper.registration(projectVO);
    }
}
