package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sys_permission")
public class Permission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="permission_id",columnDefinition="bigint(36) COMMENT'id' ")
	private Integer permissionId;
	
	@Column(name="permission_Name",columnDefinition="varchar(36) COMMENT'权限名称' ")
	private String permissionName;
	
	@Column(name="description",columnDefinition="varchar(225) COMMENT'描述' ")
	private String description;
	
	@Column(name="delete_flag",columnDefinition="tinyint(4) COMMENT'删除标记' ")
	private Short deleteFlag;

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	
}
