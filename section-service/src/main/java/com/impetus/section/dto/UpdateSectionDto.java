package com.impetus.section.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateSectionDto {
	
	@NotBlank(message="section id cannot be null or empty")
	private String sectionId;
	
	@NotBlank(message="title cannot be null or empty")
	private String title;
	
	@NotBlank(message="project name cannot be null or empty")
	private String projectName;
}
