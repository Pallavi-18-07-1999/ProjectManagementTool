package com.impetus.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.project.client.UserClient;
import com.impetus.project.dto.UserDto;
import com.impetus.project.model.ResponseModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserClient userClient;
	
	@Override
	@HystrixCommand(fallbackMethod="getDefaultUser")
	public ResponseModel<UserDto> getUser(String userId) {
		return  userClient.getUser(userId);
	}

	public ResponseModel<UserDto> getDefaultUser(String userId)
	{
		log.info("hitting fallback method : getDefaultUser");
		ResponseModel<UserDto> responseModel=new ResponseModel<>();
		responseModel.setException("cannot contact with user service");
		responseModel.setSuccess(false);
		return responseModel;
	}
}
