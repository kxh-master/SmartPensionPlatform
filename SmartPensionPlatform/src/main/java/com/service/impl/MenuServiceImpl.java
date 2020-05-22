package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bean.po.Menu;
import com.bean.po.MenuRepository;
import com.bean.vo.MenuVo;
import com.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Resource
    private MenuRepository menuRepository;
	
	/**
	 * 	根据角色id获取菜单和操作权限
	 */
    public List<MenuVo> getMenusByRoleId(Set<String> roleIds){
    	List<MenuVo> menuVos = new ArrayList<MenuVo>();
    	List<Menu> menus = menuRepository.getMenusByRoleId(roleIds);
    	if(menus!=null && !menus.isEmpty()) {
    		for(Menu menu:menus) {
    			menuVos.add(getVo(menu));
    		}
    	}
    	return menuVos;
    }
    
	@Override
	public List<MenuVo> getAllMenus() {
		List<MenuVo> menuVos = new ArrayList<MenuVo>();
		List<Menu> menus = (List<Menu>) menuRepository.findAll();
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
