package com.impetus.section.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.impetus.section.model.SectionModel;

@Repository
public interface SectionRepository extends CrudRepository<SectionModel, String> {

	List<SectionModel> findByProjectName(String projectName);

}
