package com.impetus.project.dto;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ProjectDto {
	@NotBlank(message="project name cannot be null or empty")
	private String name;


	@NotNull(message="start date cannot be null")
	@FutureOrPresent(message="start date should be of future or present")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	private LocalDate startDate;

	@NotNull(message="end date cannot be null")
	@FutureOrPresent(message="end date should be of future or present")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	private LocalDate endDate;
	
	@NotBlank(message="created by cannot be null or empty")
	private String createdBy;
}
