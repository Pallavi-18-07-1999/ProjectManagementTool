package com.impetus.task.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.impetus.task.constants.ApplicationConstants.TaskStatus;
import com.impetus.task.model.TaskModel;

public interface TaskRepository extends CrudRepository<TaskModel, String> {

	List<TaskModel> findBySectionId(String sectionId);
	
	List<TaskModel> findByAssigneeIdAndStatus(String assigneeId, TaskStatus todo);

}
