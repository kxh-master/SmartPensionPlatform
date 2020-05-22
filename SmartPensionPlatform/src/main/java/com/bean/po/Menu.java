package com.bean.po;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="sys_menu")
public class Menu implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="menu_id",columnDefinition="bigint(36) COMMENT'菜单id' ")
	private Integer menuId;
	
	@Column(name="menu_name",columnDefinition="varchar(50) COMMENT'菜单名' ")
	private String menuName;
 
	@Column(name="menu_url",columnDefinition="varchar(225) COMMENT'菜单url' ")
	private String menuUrl;
	
	@Column(name="parent_id",columnDefinition="bigint(36) COMMENT'父类id' ")
	private Integer parentId;
	
	@Column(name="menu_type",columnDefinition="tinyint(4) COMMENT'菜单类型,1:菜单组,2:菜单,3:按钮' ")
	private Short menuType;
	
	@Column(name="",columnDefinition="varchar(50) COMMENT'图标'  ")
	private String icon;
	
	@Column(name="permission",columnDefinition="varchar(50) COMMENT'权限' ")
	private String permission;
	
	@Column(name="show_flag",columnDefinition="tinyint(4) COMMENT'展示标记，0:显示，1:隐藏' ")
	private Short showFlag;
	
	@Column(name="delete_flag",columnDefinition="tinyint(4) COMMENT'删除标记，0:未删除，1:已删除' ")
	private Short deleteFlag;
	//在roles中已经明确了映射关系，这里只要一下配置就好
	@ManyToMany(mappedBy="menus")
	private Set<Role> roles = new HashSet<Role>();
	
	@Column(name="add_time",columnDefinition="datetime COMMENT'添加时间' ")
	private Date addTime;
	
	@Column(name="update_time",columnDefinition="datetime COMMENT'修改时间' ")
	private Date updateTime;
	
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Short getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public Short getMenuType() {
		return menuType;
	}
	public void setMenuType(Short menuType) {
		this.menuType = menuType;
	}
	public Short getShowFlag() {
		return showFlag;
	}
	public void setShowFlag(Short showFlag) {
		this.showFlag = showFlag;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	
	
	
	
}

	
