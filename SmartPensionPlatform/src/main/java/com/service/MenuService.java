package com.service;

import java.util.List;
import java.util.Set;

import com.bean.vo.MenuVo;


public interface MenuService {
	
	public List<MenuVo> getMenusByRoleId(Set<String> roleId);
	
	public List<MenuVo> getAllMenus();

}
