package com.impetus.project.dto;

import lombok.Data;

@Data
public class GetMemberDto {
	private String name;
	private String userId;
	private boolean active;
}
