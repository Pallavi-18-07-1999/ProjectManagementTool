package com.impetus.project.service;

import java.util.List;
import java.util.Set;

import com.impetus.project.dto.GetProjectDto;
import com.impetus.project.dto.ProjectDto;
import com.impetus.project.model.ProjectModel;

public interface ProjectService {
	public List<GetProjectDto> getAllProjects();
	public List<GetProjectDto> getProjectsByUserId(String userId);
	public boolean addProject(ProjectModel projectModel);
	public void updateProject(ProjectDto projectDto);
	public void deleteProject(String name);
	public void addMembers(String projectName,Set<String> userIds);
	public void removeMembers(String projectName,Set<String> userIds);
}