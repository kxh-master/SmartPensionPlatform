package com.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class RoleVo implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String roleId;
	
	private String roleName;
	
	private String roleAlias;
	
	private Short deleteFlag;
	
	private Date addTime;
	
	private Date updateTime;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Short getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getRoleAlias() {
		return roleAlias;
	}

	public void setRoleAlias(String roleAlias) {
		this.roleAlias = roleAlias;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
	
	
	

