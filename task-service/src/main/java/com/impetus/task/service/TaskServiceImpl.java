package com.impetus.task.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.task.constants.ApplicationConstants.TaskStatus;
import com.impetus.task.dao.TaskRepository;
import com.impetus.task.dto.GetTaskDto;
import com.impetus.task.exception.DaoException;
import com.impetus.task.model.TaskModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<GetTaskDto> getTasksBySectionId(String sectionId) {
		try
		{
			List<TaskModel> taskModels = taskRepository.findBySectionId(sectionId);
			log.info("tasks by section id fetched successfully from db");
			return taskModels.stream().map(taskModel -> {
				GetTaskDto taskDto = new GetTaskDto();
				taskDto.setTaskId(taskModel.getTaskId());
				taskDto.setEndDate(taskModel.getEndDate());
				taskDto.setPriority(taskModel.getPriority());
				taskDto.setAssigneeId(taskModel.getAssigneeId());
				taskDto.setReporterId(taskModel.getReporterId());
				taskDto.setStatus(taskModel.getStatus());
				taskDto.setSectionId(taskModel.getSectionId());
				taskDto.setTitle(taskModel.getTitle());
				taskDto.setDescription(taskModel.getDescription());
				taskDto.setCreatedAt(taskModel.getCreatedAt());
				taskDto.setUpdatedAt(taskModel.getUpdatedAt());
				return taskDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
		
	}

	@Override
	public void addTask(TaskModel taskModel) {
		try
		{
			taskRepository.save(taskModel);
			log.info("new task successfully added to db");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public void updateTask(TaskModel taskModel) {
		try
		{
			taskRepository.save(taskModel);
			log.info("task updated successfully");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}

	}

	@Override
	public void deleteTask(String taskId) {
		try
		{
			taskRepository.deleteById(taskId);
			log.info("Given task deleted successfully");
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

	@Override
	public List<GetTaskDto> getTasksByAssigneeId(String assigneeId) {
		try
		{
			List<TaskModel> taskModels = taskRepository.findByAssigneeIdAndStatus(assigneeId,TaskStatus.TODO);
			log.info("tasks fetched successfully by assignee id from db");
			return taskModels.stream().map(taskModel -> {
				GetTaskDto taskDto = new GetTaskDto();
				taskDto.setTaskId(taskModel.getTaskId());
				taskDto.setEndDate(taskModel.getEndDate());
				taskDto.setPriority(taskModel.getPriority());
				taskDto.setAssigneeId(taskModel.getAssigneeId());
				taskDto.setReporterId(taskModel.getReporterId());
				taskDto.setStatus(taskModel.getStatus());
				taskDto.setSectionId(taskModel.getSectionId());
				taskDto.setTitle(taskModel.getTitle());
				taskDto.setDescription(taskModel.getDescription());
				taskDto.setCreatedAt(taskModel.getCreatedAt());
				taskDto.setUpdatedAt(taskModel.getUpdatedAt());
				return taskDto;
			}).collect(Collectors.toList());
		}catch(Exception e)
		{
			throw new DaoException(e.getMessage());
		}
	}

}
