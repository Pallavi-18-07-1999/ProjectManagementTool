package com.impetus.authorization.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.authorization.dao.UserRepository;
import com.impetus.authorization.dto.UserDto;
import com.impetus.authorization.exception.DaoException;
import com.impetus.authorization.model.UserModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public void addUser(UserModel userModel) {
		try
		{
			userRepository.save(userModel);
			log.info("user added successfully");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public UserDto getUser(String userId) {
		try
		{
			Optional<UserModel> userModelOptional=userRepository.findById(userId);
			UserModel userModel=userModelOptional.get();
			UserDto userDto=new UserDto();
			userDto.setUserId(userModel.getUserId());
			userDto.setActive(userModel.isActive());
			userDto.setPassword(userModel.getPassword());
			userDto.setName(userModel.getName());
			userDto.setRoleId(userModel.getRoleModel().getRoleId());
			log.info("user fetched successfully");
			return userDto;	
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}
	
	public List<UserDto> getUsers(boolean active)
	{
		try
		{

			List<UserModel> userModels=userRepository.findByActive(active);

			log.info(" users fetched");
			return userModels.stream().map(userModel->{
				UserDto userDto=new UserDto();
				userDto.setUserId(userModel.getUserId());
				userDto.setActive(userModel.isActive());
				userDto.setName(userModel.getName());
				userDto.setRoleId(userModel.getRoleModel().getRoleId());
				return userDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
		
	}


}
