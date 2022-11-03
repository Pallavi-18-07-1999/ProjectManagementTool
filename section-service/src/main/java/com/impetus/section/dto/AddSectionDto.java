package com.impetus.section.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddSectionDto {
	
	@NotBlank(message="section title cannot be null or empty")
	private String title;
	
	@NotBlank(message="project name cannot be null or empty")
	private String projectName;
}
