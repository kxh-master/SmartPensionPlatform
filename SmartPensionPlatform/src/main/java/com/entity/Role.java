package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class Role {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id",columnDefinition="varchar(36) COMMENT'id' ")
	private String roleId;
	
	@Column(name="role_name",columnDefinition="varchar(30) COMMENT'角色名' ")
	private String roleName;
	
	@Column(name="role_alias",columnDefinition="varchar(20) COMMENT'角色别名' ")
	private String roleAlias;
	
	@Column(name="delete_flag",columnDefinition="tinyint(4) COMMENT'删除标记，0:未删除，1:已删除' ")
	private Short deleteFlag;
	
	//这里的roles是user实体类中的 roles实体类起的名字，要一一对应
	@ManyToMany(mappedBy="roles")
	private Set<User> users = new HashSet<User>();
	
	@ManyToMany
	//@JoinTable:映射中间表
    //name属性是关联中间表的名称，
    // joinColumns属性表示，在保存关系中的表中，所保存关联关系的外键的字段。并配合@JoinColumn标记使用。
    //inverseJoinColumns  属性与joinColumns属性类似，它保存的是保存关系的另一个外键字段。
	@JoinTable(name="sys_role_menu",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="menu_id"))
	private Set<Menu> menus = new HashSet<Menu>();
	
	@Column(name="add_time",columnDefinition="datetime COMMENT'添加时间' ")
	private Date addTime;
	
	@Column(name="update_time",columnDefinition="datetime COMMENT'修改时间' ")
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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Menu> getMenus() {
		return menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
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
	
	
	

