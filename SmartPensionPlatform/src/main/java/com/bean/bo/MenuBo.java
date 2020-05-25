package com.bean.bo;

import java.util.Date;
import java.util.List;

import com.bean.vo.MenuVo;

public class MenuBo {

	private String menuId;
	
	private String menuName;
 
	private String menuUrl;
	
	private String parentId;
	
	private Short menuType;
	
	private String icon;
	
	private String permission;
	
	private Short showFlag;
	
	private Short deleteFlag;
	
	private Date addTime;
	
	private Date updateTime;
	
	private Integer sortno;
	
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
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

	public Short getMenuType() {
		return menuType;
	}

	public void setMenuType(Short menuType) {
		this.menuType = menuType;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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

	public Short getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSortno() {
		return sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	
}
