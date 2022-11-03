package com.impetus.section.service;

import java.util.List;

import com.impetus.section.dto.GetTaskDto;
import com.impetus.section.model.ResponseModel;

public interface TaskService {
	
	public ResponseModel<List<GetTaskDto>> getTasks(String sectionId);
	public ResponseModel<List<GetTaskDto>> getDefaultTasks(String sectionId);
}
