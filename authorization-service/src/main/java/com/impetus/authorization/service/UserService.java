package com.impetus.authorization.service;

import java.util.List;

import com.impetus.authorization.dto.UserDto;
import com.impetus.authorization.model.UserModel;

public interface UserService {
	public void addUser(UserModel userModel);
	public UserDto getUser(String userId);
	public List<UserDto> getUsers(boolean active);
}
