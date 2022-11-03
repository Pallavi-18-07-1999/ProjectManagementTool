package com.impetus.section.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.section.dao.SectionRepository;
import com.impetus.section.dto.GetSectionDto;
import com.impetus.section.dto.GetTaskDto;
import com.impetus.section.exception.DaoException;
import com.impetus.section.model.ResponseModel;
import com.impetus.section.model.SectionModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SectionServiceImpl implements SectionService {

	@Autowired
	private SectionRepository sectionRepository;

	@Autowired
	private TaskService taskService;

	@Override
	public void addSection(SectionModel sectionModel) {
		try
		{
			sectionRepository.save(sectionModel);
			log.info("new section added successfully");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	@Override
	public void deleteSection(String title) {
		try
		{
			sectionRepository.deleteById(title);
			log.info("section deleted successfully from db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	@Override
	public List<GetSectionDto> getSections(String projectName) {
		try
		{
			List<SectionModel> sectionModels = sectionRepository.findByProjectName(projectName);
			log.info("sections by project name fetched successfully from db");
			return sectionModels.stream().map(sectionModel -> {
				GetSectionDto sectionDto = new GetSectionDto();
				sectionDto.setTitle(sectionModel.getTitle());
				sectionDto.setProjectName(sectionModel.getProjectName());
				sectionDto.setSectionId(sectionModel.getSectionId());
				ResponseModel<List<GetTaskDto>> responseModel=taskService.getTasks(sectionModel.getSectionId());
				if(responseModel.isSuccess()==false)
				{
					throw new DaoException(responseModel.getException());
				}
				else
					sectionDto.setTasks(responseModel.getResponse());
				return sectionDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateSection(SectionModel sectionModel) {
		try
		{
			sectionRepository.save(sectionModel);
			log.info("section updated successfully in db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
		
	}

}
