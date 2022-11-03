package com.impetus.section.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.impetus.section.dto.AddSectionDto;
import com.impetus.section.dto.GetSectionDto;
import com.impetus.section.dto.UpdateSectionDto;
import com.impetus.section.model.ResponseModel;
import com.impetus.section.model.SectionModel;
import com.impetus.section.service.SectionService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SectionController {
	
	@Autowired
	private SectionService sectionService;
	
	@GetMapping("/{projectName}")
	public ResponseEntity<ResponseModel<List<GetSectionDto>>> getSection(@PathVariable("projectName") String projectName)
	{
		List<GetSectionDto> sectionList=sectionService.getSections(projectName);
		log.info("sections fetched successfully");
		ResponseModel<List<GetSectionDto>> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse(sectionList);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseModel<String>> addSection(@RequestBody @Valid AddSectionDto sectionDto)
	{
		SectionModel sectionModel=new SectionModel();
		sectionModel.setTitle(sectionDto.getTitle());
		sectionModel.setProjectName(sectionDto.getProjectName());
		sectionService.addSection(sectionModel);
		log.info("section added successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setResponse("Section added successfully");
		responseModel.setSuccess(true);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ResponseModel<String>> updateSection(@RequestBody @Valid UpdateSectionDto sectionDto)
	{
		SectionModel sectionModel=new SectionModel();
		sectionModel.setTitle(sectionDto.getTitle());
		sectionModel.setProjectName(sectionDto.getProjectName());
		sectionModel.setSectionId(sectionDto.getSectionId());
		sectionService.updateSection(sectionModel);
		log.info("section updated successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setResponse("Section updated successfully");
		responseModel.setSuccess(true);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@DeleteMapping("{sectionId}")
	public ResponseEntity<ResponseModel<String>> deleteSection(@PathVariable("sectionId") String sectionId)
	{
		sectionService.deleteSection(sectionId);
		log.info("section deleted successfully");
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("Section deleted successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
}
