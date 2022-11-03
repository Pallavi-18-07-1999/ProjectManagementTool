package com.impetus.section.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.section.dto.GetTaskDto;
import com.impetus.section.feignclient.TaskClient;
import com.impetus.section.model.ResponseModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskClient taskClient;
	
	@HystrixCommand(fallbackMethod="getDefaultTasks")
	public ResponseModel<List<GetTaskDto>> getTasks(String sectionId)
	{
		return  taskClient.getTasks("sections",sectionId);
	}
	
	
	public ResponseModel<List<GetTaskDto>> getDefaultTasks(String sectionId)
	{
		log.debug("hitting fallback method : getDefaultTasks");
		ResponseModel<List<GetTaskDto>> responseModel=new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setException("cannot fetch tasks");
		return responseModel;
	}
}