package com.impetus.section.dto;

import java.util.List;

import lombok.Data;

@Data
public class GetSectionDto {
	private String sectionId;
	private String title;
	private String projectName;
	List<GetTaskDto> tasks;
}
