package com.impetus.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.project.dao.MemberRepository;
import com.impetus.project.dao.ProjectRepository;
import com.impetus.project.dto.GetMemberDto;
import com.impetus.project.dto.GetProjectDto;
import com.impetus.project.dto.ProjectDto;
import com.impetus.project.dto.UserDto;
import com.impetus.project.exception.DaoException;
import com.impetus.project.model.MemberModel;
import com.impetus.project.model.ProjectModel;
import com.impetus.project.model.ResponseModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<GetProjectDto> getProjectsByUserId(String userId) {
		try
		{
			List<MemberModel> memberModelList=this.memberRepository.findByUserId(userId);
			List<String> names=new ArrayList<>();
			for(MemberModel model:memberModelList)
			{
				names.add(model.getProjectModel().getName());
			}
			List<ProjectModel> projectModelList = this.projectRepository.findByNameIn(names);
			log.info("projects and memberss fetched successfully from db");
			return projectModelList.stream().map(projectModel -> {
				GetProjectDto projectDto = new GetProjectDto();
				projectDto.setName(projectModel.getName());
				projectDto.setCreatedBy(projectModel.getCreatedBy());
				projectDto.setStartDate(projectModel.getStartDate());
				projectDto.setEndDate(projectModel.getEndDate());
				List<MemberModel> memberModels=memberRepository.findByProjectModel(projectModel);
				projectDto.setMembers(memberModels.stream().map(memberModel->{
					GetMemberDto memberDto=new GetMemberDto();
					memberDto.setUserId(memberModel.getUserId());
					ResponseModel<UserDto> responseModel=userService.getUser(memberModel.getUserId());
					if(responseModel.isSuccess()==false)
					{
						
					}
					UserDto userDto= responseModel.getResponse();
					memberDto.setActive(userDto.isActive());
					memberDto.setName(userDto.getName());
					return memberDto;
				}).collect(Collectors.toSet()));
				return projectDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
		
	}

	@Override
	public boolean addProject(ProjectModel projectModel) {
		try
		{
			Optional<ProjectModel> projectModelOptional=projectRepository.findById(projectModel.getName());
			if(projectModelOptional.isPresent())
			{
				log.info("cannot add project as another exist with the same name");
				return false;
			}
			projectRepository.save(projectModel);
			log.info("project added successfully from db");
			return true;
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateProject(ProjectDto projectDto) {
		try
		{
			Optional<ProjectModel> projectModelOptional=projectRepository.findById(projectDto.getName());
			ProjectModel projectModel=projectModelOptional.get();
			projectModel.setStartDate(projectDto.getStartDate());
			projectModel.setEndDate(projectDto.getStartDate());
			projectRepository.save(projectModel);
			log.info("project info updated successfully in db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void deleteProject(String name) {
		try
		{
			ProjectModel projectModel=new ProjectModel();
			projectModel.setName(name);
			memberRepository.deleteByProjectModel(projectModel);
			projectRepository.deleteById(name);	
			log.info("project deleted successfully from db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<GetProjectDto> getAllProjects() {
		try
		{
			List<ProjectModel> projectModelList = (List<ProjectModel>) this.projectRepository.findAll();
			log.info("projects fetched successfully from database");
			return projectModelList.stream().map(projectModel -> {
				GetProjectDto projectDto = new GetProjectDto();
				projectDto.setName(projectModel.getName());
				projectDto.setCreatedBy(projectModel.getCreatedBy());
				projectDto.setStartDate(projectModel.getStartDate());
				projectDto.setEndDate(projectModel.getEndDate());

				List<MemberModel> memberModels=memberRepository.findByProjectModel(projectModel);
				projectDto.setMembers(memberModels.stream().map(memberModel->{
					GetMemberDto memberDto=new GetMemberDto();
					memberDto.setUserId(memberModel.getUserId());
					UserDto userDto=(UserDto) userService.getUser(memberModel.getUserId()).getResponse();
					memberDto.setActive(userDto.isActive());
					memberDto.setName(userDto.getName());
					return memberDto;
				}).collect(Collectors.toSet()));
				return projectDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
		
	}

	@Override
	public void addMembers(String projectName, Set<String> userIds) {
		try
		{
			ProjectModel projectModel=new ProjectModel();
			projectModel.setName(projectName);
			List<MemberModel> memberModels=new ArrayList<>();
			for(String userId:userIds)
			{
				MemberModel model=new MemberModel();
				model.setUserId(userId);
				model.setProjectModel(projectModel);
				memberModels.add(model);
			}
			memberRepository.saveAll(memberModels);
			log.info("new members added successfully to db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public void removeMembers(String projectName, Set<String> userIds) {
		try
		{
			ProjectModel projectModel=new ProjectModel();
			projectModel.setName(projectName);
			memberRepository.deleteByProjectModelAndUserIdIn(projectModel,userIds);
			log.info("members removed successfully from db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

}
