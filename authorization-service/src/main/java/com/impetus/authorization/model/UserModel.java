package com.impetus.authorization.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;


@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
	@Id
	@Column(name="user_id")
	private String userId;

	@Column(name="name")
	private String name;

	@Column(name="password")
	private String password;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private RoleModel roleModel;
	
	@Column(name="active")
	private boolean active;
	
}
