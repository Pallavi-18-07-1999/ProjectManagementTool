package com.impetus.section.dto;

import java.util.Date;
import java.util.List;

import com.impetus.section.constants.ApplicationConstants.TaskPriority;
import com.impetus.section.constants.ApplicationConstants.TaskStatus;
import com.impetus.section.dto.GetTaskDto;

public class GetTaskDto {
	private String taskId;

	private String title;

	private String description;

	private Date startDate;

	private Date endDate;

	private String assigneeId;

	private String reporterId;

	private TaskStatus status;

	private TaskPriority priority;

	private String sectionId;

	private List<GetTaskDto> subtasks;
}
