package com.impetus.task.service;

import java.util.List;

import com.impetus.task.dto.GetTaskDto;
import com.impetus.task.model.TaskModel;

public interface TaskService {
	public List<GetTaskDto> getTasksBySectionId(String sectionId);
	
	public List<GetTaskDto> getTasksByAssigneeId(String sectionId);
	
	public void addTask(TaskModel taskModel);

	public void updateTask(TaskModel taskModel);

	public void deleteTask(String taskId);
}
