package com.service;

import java.util.Set;


import com.bean.vo.MenuVo;


public interface MenuService {
	
	public Set<MenuVo> getMenusByRoleId(Set<String> roleId);

}
