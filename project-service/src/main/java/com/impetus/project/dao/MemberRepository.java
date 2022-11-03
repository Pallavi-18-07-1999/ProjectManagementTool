package com.impetus.project.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.project.model.MemberModel;
import com.impetus.project.model.ProjectModel;

@Repository
public interface MemberRepository extends CrudRepository<MemberModel,String>{

	List<MemberModel> findByUserId(String userId);

	List<MemberModel> findByUserIdIn(List<String> userIds);

	List<MemberModel> findByProjectModel(ProjectModel projectModel);

	void deleteByProjectModelAndUserIdIn(ProjectModel projectModel, Set<String> userIds);

	void deleteByProjectModel(ProjectModel projectModel);

	
}
