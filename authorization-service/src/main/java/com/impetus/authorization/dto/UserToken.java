package com.impetus.authorization.dto;

import lombok.Data;

@Data
public class UserToken {
	private String userId;
	private String token;
	private String role;
}
