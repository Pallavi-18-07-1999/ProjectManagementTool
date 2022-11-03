package com.impetus.task.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.impetus.task.constants.ApplicationConstants.TaskPriority;
import com.impetus.task.constants.ApplicationConstants.TaskStatus;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "task")
@Getter
@Setter
public class TaskModel {

	private static final String ALPHANUMERICSTRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	@Column(name = "task_id")
	@Id
	private String taskId;

	@Column(name = "title")
	private String title;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(name="updated_at")
	private Date updatedAt;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "assignee_id")
	private String assigneeId;

	@Column(name = "reporter_id")
	private String reporterId;

	@Enumerated(EnumType.STRING)
	private TaskStatus status;

	@Enumerated(EnumType.STRING)
	private TaskPriority priority;

	@Column(name = "section_id")
	private String sectionId;

	@PrePersist
	public void generateTaskId() {
		int num = (int) (Math.random() * 10);
		String timeStr = String.valueOf(System.currentTimeMillis());
		taskId = ALPHANUMERICSTRING.substring(num, num + 3) + timeStr.substring(timeStr.length() - 3);
	}
}
