package com.impetus.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberModel {
	
	private static final String ALPHANUMERICSTRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	@Id
	@Column
	private String memberId;
	
	@Column
	private String userId;
	
	@ManyToOne
	@JoinColumn(name="project_name")
	private ProjectModel projectModel;
	
	@PrePersist
	public void generateMemberId()
	{
		int num = (int) (Math.random() * 62);
		String timeStr = String.valueOf(System.currentTimeMillis());
		memberId = ALPHANUMERICSTRING.substring(num, num + 3) + timeStr.substring(timeStr.length() - 3);
	}
	

}
