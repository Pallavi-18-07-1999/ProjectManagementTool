package com.impetus.section.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.Data;

@Entity
@Table(name = "section")
@Data
public class SectionModel {

	private static final String ALPHANUMERICSTRING="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	@Column
	@Id
	private String sectionId;
	
	@Column
	private String title;

	@Column
	private String projectName;
	
	@PrePersist
	public void generateSectionId()
	{
		int num=(int) (Math.random()*62);
		String timeStr=String.valueOf(System.currentTimeMillis());
		sectionId=ALPHANUMERICSTRING.substring(num,num+3)+timeStr.substring(timeStr.length()-3);
	}
}
