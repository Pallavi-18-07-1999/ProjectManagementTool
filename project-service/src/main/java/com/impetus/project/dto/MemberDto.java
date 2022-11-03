package com.impetus.project.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class MemberDto {
	
	@NotEmpty(message="list of user ids cannot be null or empty")
	private Set<String> userIds;
}
