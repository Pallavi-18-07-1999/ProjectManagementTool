package com.impetus.project.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.impetus.project.dto.UserDto;
import com.impetus.project.model.ResponseModel;

@FeignClient("authorization-service")
public interface UserClient {
	@RequestMapping(method=RequestMethod.GET,path="/users/{userId}")
	public ResponseModel<UserDto> getUser(@PathVariable("userId") String userId);
}
