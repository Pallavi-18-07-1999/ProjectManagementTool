package com.impetus.authorization.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.authorization.dto.UserDto;
import com.impetus.authorization.dto.UserLoginCredential;
import com.impetus.authorization.dto.UserToken;
import com.impetus.authorization.model.ResponseModel;
import com.impetus.authorization.service.CustomerDetailsService;
import com.impetus.authorization.service.UserService;
import com.impetus.authorization.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtil jwtUtil;
	
	

	@PostMapping("/login")
	public ResponseEntity<ResponseModel<UserToken>> login(@RequestBody @Valid UserLoginCredential userLoginCredentials) {
		String userId = userLoginCredentials.getUserId();
		String password = userLoginCredentials.getPassword();
		ResponseModel<UserToken> responseModel = new ResponseModel<>();
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userId, password));
		UserDetails userDetails = customerDetailsService.loadUserByUsername(userId);
		UserToken userToken = new UserToken();
		userToken.setUserId(userId);
		userToken.setToken(jwtUtil.generateToken(userDetails));
		SimpleGrantedAuthority sga=(SimpleGrantedAuthority) userDetails.getAuthorities().toArray()[0];
		userToken.setRole(sga.getAuthority());
		log.info("user token generated successfully");
		responseModel.setSuccess(true);
		responseModel.setResponse(userToken);
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}
	
	
	
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<ResponseModel<UserDto>> getUser(@PathVariable("userId") String userId)
	{
		UserDto userDto=userService.getUser(userId);
		log.info("user fetched successfully");
		ResponseModel<UserDto> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse(userDto);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseModel<List<com.impetus.authorization.dto.UserDto>>> getActiveUsers(@RequestParam("active") boolean active)
	{
		List<com.impetus.authorization.dto.UserDto> userDtos=userService.getUsers(active);
		log.info("user fetched successfully");
		ResponseModel<List<UserDto>> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse(userDtos);
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	
	
	
	
}
