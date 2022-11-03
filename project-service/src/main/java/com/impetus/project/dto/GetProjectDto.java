package com.impetus.project.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.Data;

@Data
public class GetProjectDto {
	private String name;

	private String manager;

	private LocalDate startDate;

	private LocalDate endDate;
	
	private String createdBy;
	
	private Set<GetMemberDto> members;
}
