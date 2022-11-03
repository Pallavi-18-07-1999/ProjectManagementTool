package com.impetus.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	
	private String userId;

	private String name;

	private boolean active;
	
	private int roleId;
	
	private String password;


}
