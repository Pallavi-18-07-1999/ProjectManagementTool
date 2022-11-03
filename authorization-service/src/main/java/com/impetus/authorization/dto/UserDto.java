package com.impetus.authorization.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
	@NotNull(message="email cannot be null")
	@NotBlank(message="email cannot be empty")
	@Email
	private String userId;

	@NotNull(message="user name cannot be null")
	@NotBlank(message="name cannot be empty")
	private String name;

	@NotNull(message="password cannot be null")
	@Size(min=8,message="password too short")
	@Size(max=15,message = "password too long")
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",message="Password must contain lowercase,uppercase alphabets,numbers and special character")
	private String password;

	private boolean active;
	
	private int roleId;
}
