package com.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class MenuVo implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer menuId;
	
	private String menuName;
 
	private String menuUrl;
	
	private Integer parentId;
	
	private Short menuType;
	
	private String icon;
	
	private String permission;
	
	private Short showFlag;
	
	private Short deleteFlag;
	
	private Date addTime;
	
	private Date updateTime;
	
	private List<MenuVo> children;
	
	private Boolean hidden;
	
	private Boolean isMenuGroup;
	
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
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
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
	public Short getMenuType() {
		return menuType;
	}
	public void setMenuType(Short menuType) {
		this.menuType = menuType;
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
	public List<MenuVo> getChildren() {
		return children;
	}
	public void setChildren(List<MenuVo> children) {
		this.children = children;
	}
	public Boolean getHidden() {
		return hidden;
	}
	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}
	public Boolean getIsMenuGroup() {
		return isMenuGroup;
	}
	public void setIsMenuGroup(Boolean isMenuGroup) {
		this.isMenuGroup = isMenuGroup;
	}
	
	
	
}

	
