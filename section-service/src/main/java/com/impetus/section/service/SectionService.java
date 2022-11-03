package com.impetus.section.service;

import java.util.List;

import com.impetus.section.dto.GetSectionDto;
import com.impetus.section.model.SectionModel;

public interface SectionService {
	public void addSection(SectionModel sectionModel);
	
	public void updateSection(SectionModel sectionModel);

	public void deleteSection(String title);

	public List<GetSectionDto> getSections(String projectName);

}
