package com.impetus.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "role")
@Data
public class RoleModel {

	@Column(name = "role_id")
	@Id
	private int roleId;

	@Column(name = "role_name")
	private String roleName;
}
