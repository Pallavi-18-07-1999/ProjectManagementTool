package com.impetus.task.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impetus.task.constants.ApplicationConstants.TaskPriority;

import lombok.Data;

@Data
public class AddTaskDto {
	
	@NotBlank(message="task title cannot be null or empty")
	private String title;

	@NotBlank(message="description cannot be null or empty")
	private String description;

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

	@NotNull(message="priority cannot be null")
	private TaskPriority priority;

	@NotBlank(message="section id cannot be null or empty")
	private String sectionId;
	
}
