package com.impetus.project.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "project")
@Data
public class ProjectModel {
	@Id
	@Column
	private String name;

	@Column
	private LocalDate startDate;

	@Column
	private LocalDate endDate;

	@Column
	private String createdBy;
}
