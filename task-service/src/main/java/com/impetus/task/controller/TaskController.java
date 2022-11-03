package com.impetus.task.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.impetus.task.constants.ApplicationConstants.TaskStatus;
import com.impetus.task.dto.AddTaskDto;
import com.impetus.task.dto.GetTaskDto;
import com.impetus.task.dto.UpdateTaskDto;
import com.impetus.task.model.ResponseModel;
import com.impetus.task.model.TaskModel;
import com.impetus.task.service.TaskService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping
	public ResponseEntity<ResponseModel<List<GetTaskDto>>> getTasksBySectionId(@RequestParam("type") String type,@RequestParam("data") String data) {
		 ResponseModel<List<GetTaskDto>> responseModel=new ResponseModel<>();
		 if(type.equals("sections"))
			 responseModel.setResponse(taskService.getTasksBySectionId(data));
		 else
			 responseModel.setResponse(taskService.getTasksByAssigneeId(data));
		 responseModel.setSuccess(true);
		 log.info("tasks fetched succesfully");
		 return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ResponseModel<String>> addTask(@RequestBody @Valid AddTaskDto taskDto)
	{
		TaskModel taskModel=new TaskModel();
		taskModel.setTitle(taskDto.getTitle());
		taskModel.setDescription(taskDto.getDescription());
		taskModel.setCreatedAt(new Date());
		taskModel.setEndDate(taskDto.getEndDate());
		taskModel.setSectionId(taskDto.getSectionId());
		taskModel.setAssigneeId(taskDto.getAssigneeId());
		taskModel.setReporterId(taskDto.getReporterId());
		taskModel.setStatus(TaskStatus.TODO);
		taskModel.setPriority(taskDto.getPriority());
		taskService.addTask(taskModel);
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("task added successfully");
		log.info("new task added successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@DeleteMapping("{taskId}")
	public ResponseEntity<ResponseModel<String>> deleteTask(@PathVariable("taskId") String taskId)
	{
		taskService.deleteTask(taskId);
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("task deleted successfully");
		log.info("task deleted successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ResponseModel<String>> updateTask(@RequestBody @Valid UpdateTaskDto taskDto)
	{
		TaskModel taskModel=new TaskModel();
		taskModel.setTaskId(taskDto.getTaskId());
		taskModel.setTitle(taskDto.getTitle());
		taskModel.setDescription(taskDto.getDescription());
		taskModel.setCreatedAt(taskDto.getCreatedAt());
		taskModel.setEndDate(taskDto.getEndDate());
		taskModel.setUpdatedAt(new Date());
		taskModel.setSectionId(taskDto.getSectionId());
		taskModel.setAssigneeId(taskDto.getAssigneeId());
		taskModel.setReporterId(taskDto.getReporterId());
		taskModel.setStatus(taskDto.getStatus());
		taskModel.setPriority(taskDto.getPriority());
		taskService.updateTask(taskModel);
		ResponseModel<String> responseModel=new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setResponse("task updated successfully");
		log.info("task updated successfully");
		return new ResponseEntity<>(responseModel,HttpStatus.OK);
	}
	
	
}


