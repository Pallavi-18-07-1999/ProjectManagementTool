package com.impetus.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.project.model.ProjectModel;

@Repository
public interface ProjectRepository extends CrudRepository<ProjectModel, String> {

	List<ProjectModel> findByNameIn(List<String> names);


}
