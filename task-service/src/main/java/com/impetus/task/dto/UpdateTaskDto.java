package com.impetus.task.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impetus.task.constants.ApplicationConstants.TaskPriority;
import com.impetus.task.constants.ApplicationConstants.TaskStatus;

import lombok.Data;

@Data
public class UpdateTaskDto {
	@NotBlank(message="task id cannot be null or empty")
	private String taskId;
	
	@NotBlank(message="title cannot be null or empty")
	private String title;

	@NotBlank(message="description cannot be null or empty")
	private String description;
	
	@NotNull(message="end date cannot be null")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy hh/mm/ss")
	private Date createdAt;

	@FutureOrPresent(message="end date should be in future or present")
	@NotNull(message="end date cannot be null")
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd/MM/yyyy")
	private LocalDate endDate;

	@Email(message="assignee id should be of the correct format")
	@NotBlank(message="assignee_id cannot be null or empty")
	private String assigneeId;

	@Email(message="reporter id should be of the correct format")
	@NotBlank(message="reporter_id cannot be null or empty")
	private String reporterId;

	@NotNull(message="status cannot be null")
	private TaskStatus status;

	@NotNull(message="priority cannot be null")
	private TaskPriority priority;

	@NotBlank(message="section id cannot be null or empty")
	private String sectionId;
	
}
