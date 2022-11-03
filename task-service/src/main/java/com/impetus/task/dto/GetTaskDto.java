package com.impetus.task.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.impetus.task.constants.ApplicationConstants.TaskPriority;
import com.impetus.task.constants.ApplicationConstants.TaskStatus;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class GetTaskDto {
	private String taskId;

	private String title;

	private String description;

	private Date createdAt;

	private Date updatedAt;
	
	private LocalDate endDate;

	private String assigneeId;

	private String reporterId;

	private TaskStatus status;

	private TaskPriority priority;

	private String sectionId;

}
