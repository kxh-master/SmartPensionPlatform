package com.service;

import java.util.List;
import java.util.Set;

import com.bean.bo.MenuBo;
import com.bean.po.Menu;
import com.bean.vo.MenuVo;


public interface MenuService {
	
	public List<MenuVo> getMenusByRoleId(Set<String> roleId);
	
	public List<MenuVo> getMenuList();
	
	public List<Menu> getAllMenus();
	
	public Menu addMenu(MenuBo menuBo);
	
	public MenuVo getMenu(String menuId);
	
	public Menu updateMenu(MenuBo menuBo);

}
