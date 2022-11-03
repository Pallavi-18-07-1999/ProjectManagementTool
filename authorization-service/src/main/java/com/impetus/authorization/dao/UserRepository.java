package com.impetus.authorization.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.authorization.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel,String> {

	List<UserModel> findByActive(boolean b);


}
