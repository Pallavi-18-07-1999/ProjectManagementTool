package com.impetus.project.service;

import com.impetus.project.dto.UserDto;
import com.impetus.project.model.ResponseModel;

public interface UserService {
	public ResponseModel<UserDto> getUser(String userId);
	
	public ResponseModel<UserDto> getDefaultUser(String userId);
}
