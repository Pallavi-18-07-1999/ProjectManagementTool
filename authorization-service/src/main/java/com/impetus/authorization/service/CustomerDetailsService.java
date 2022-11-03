
package com.impetus.authorization.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.impetus.authorization.dao.UserRepository;
import com.impetus.authorization.exception.ResourceNotFoundException;
import com.impetus.authorization.model.RoleModel;
import com.impetus.authorization.model.UserModel;

@Service
public class CustomerDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String userId) {
		Optional<UserModel> userModelOptional = userRepository.findById(userId);
		if (userModelOptional.isPresent() == false) {
			throw new ResourceNotFoundException("User with userId: " + userId + " not found");
		}
		List<SimpleGrantedAuthority> list=new ArrayList<>();
		UserModel userModel=userModelOptional.get();
		RoleModel roleModel=userModel.getRoleModel();
		SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(roleModel.getRoleName());
		list.add(simpleGrantedAuthority);
		return new User(userId, userModel.getPassword(),userModel.isActive(),true,true,true,list);
		
	}

}
