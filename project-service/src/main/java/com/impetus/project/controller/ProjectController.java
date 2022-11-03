package com.impetus.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.project.dto.GetProjectDto;
import com.impetus.project.dto.MemberDto;
import com.impetus.project.dto.ProjectDto;
import com.impetus.project.model.ProjectModel;
import com.impetus.project.model.ResponseModel;
import com.impetus.project.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping
	public ResponseEntity<ResponseModel<List<GetProjectDto>>> getProjectist(@RequestHeader("userId") String userId,@RequestHeader("roleId") String roleId)
	{
		List<GetProjectDto> projects=null;
		if(roleId.equals("ADMIN"))
		{
			log.debug("given user is admin");
			projects=projectService.getAllProjects();
		}
		else
		{
			log.debug("given user is developer");
			projects=projectService.getProjectsByUserId(userId);
		}
		log.info("projects fetched successfully");
		ResponseModel<List<GetProjectDto>> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse(projects);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<ResponseModel<String>> addProject(@RequestBody @Valid ProjectDto projectDto)
	{
		ProjectModel projectModel=new ProjectModel();
		projectModel.setName(projectDto.getName());
		projectModel.setStartDate(projectDto.getStartDate());
		projectModel.setEndDate(projectDto.getEndDate());
		projectModel.setCreatedBy(projectDto.getCreatedBy());
		boolean success=projectService.addProject(projectModel);
		if(!success)
		{
			log.info("project added successfully");
			ResponseModel<String> responseModel=new ResponseModel<>();
			responseModel.setSuccess(false);
			responseModel.setException("project cannot be added as another project exist with the same name");
			return new ResponseEntity<>(responseModel,HttpStatus.BAD_REQUEST);
		}
		else
		{
			log.info("project can't be added");
			ResponseModel<String> responseModel=new ResponseModel<>();
			responseModel.setSuccess(true);
			responseModel.setResponse("new project added successfully");
			return new ResponseEntity<>(responseModel,HttpStatus.OK);
		}
		
	}
	
	@PutMapping
	public ResponseEntity<ResponseModel<String>> updateProject(@RequestBody @Valid ProjectDto projectDto)
	{
		projectService.updateProject(projectDto);
		log.info("project info modified successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("project updated successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{name}")
	public ResponseEntity<ResponseModel<String>> deleteProject(@PathVariable("name") String name)
	{
		projectService.deleteProject(name);
		log.info("project removed successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("project deleted successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
		
	}
	
	
	@PostMapping("{projectName}/members")
	public ResponseEntity<ResponseModel<String>> addMembers(@PathVariable("projectName") String projectName,@RequestBody @Valid MemberDto memberDto)
	{
		projectService.addMembers(projectName,memberDto.getUserIds());
		log.info("members added successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("members added successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
		
	}
	
	@DeleteMapping("{projectName}/members")
	public ResponseEntity<ResponseModel<String>> removeMembers(@PathVariable("projectName") String projectName,@RequestBody @Valid MemberDto memberDto)
	{
		projectService.removeMembers(projectName,memberDto.getUserIds());
		log.info("members removed successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("members removed successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
		
	}
		
}
