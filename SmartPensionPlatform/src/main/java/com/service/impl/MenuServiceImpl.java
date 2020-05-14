package com.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bean.po.Menu;
import com.bean.po.MenuRepository;
import com.bean.po.Role;
import com.bean.vo.MenuVo;
import com.bean.vo.RoleVo;
import com.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
    private MenuRepository menuRepository;
	
	/**
	 * 	根据角色id获取菜单和操作权限
	 */
    public Set<MenuVo> getMenusByRoleId(Set<String> roleIds){
    	Set<MenuVo> menuVos = new HashSet<MenuVo>();
    	Set<Menu> menus = menuRepository.getMenusByRoleId(roleIds);
    	if(menus!=null && !menus.isEmpty()) {
    		for(Menu menu:menus) {
    			menuVos.add(getVo(menu));
    		}
    	}
    	return menuVos;
    }
    
    
    
    private MenuVo getVo(Menu po) {
    	MenuVo vo = new MenuVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}


}
