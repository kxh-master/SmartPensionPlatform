package com.bean.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class TreeTable implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String parent_id;
 
	private Integer order;
	
	private String name;
	
	private String url;
	
	private String icon;
	
	private Boolean open;
	
	private List<TreeTable> lists;
	
	private Boolean isShowCheckbox;
	
	private Boolean highlight;
	
	private Short type;
	
	private Short deleteFlag;
	
	private Date addTime;
	
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public List<TreeTable> getLists() {
		return lists;
	}

	public void setLists(List<TreeTable> lists) {
		this.lists = lists;
	}

	public Boolean getIsShowCheckbox() {
		return isShowCheckbox;
	}

	public void setIsShowCheckbox(Boolean isShowCheckbox) {
		this.isShowCheckbox = isShowCheckbox;
	}

	public Boolean getHighlight() {
		return highlight;
	}

	public void setHighlight(Boolean highlight) {
		this.highlight = highlight;
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

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}
	
	
}

	
