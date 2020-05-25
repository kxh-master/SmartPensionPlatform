package com.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.bo.MenuBo;
import com.bean.po.Menu;
import com.bean.po.MenuRepository;
import com.bean.vo.MenuVo;
import com.service.MenuService;
import com.util.BaseUtil;

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
    			MenuVo menuVo = new MenuVo();
    			BaseUtil.copyProperties(menu,menuVo);
    			menuVos.add(menuVo);
    		}
    	}
    	return menuVos;
    }
    
    /**
     * 查询未删除的数据
     */
    public List<MenuVo> getMenuList(){
    	List<MenuVo> menuVos = new ArrayList<MenuVo>();
		List<Menu> menus = (List<Menu>) menuRepository.getMenuList();
		if(menus!=null && !menus.isEmpty()) {
    		for(Menu menu:menus) {
    			MenuVo menuVo = new MenuVo();
    			BaseUtil.copyProperties(menu,menuVo);
    			menuVos.add(menuVo);
    		}
    	}
		return menuVos;
    }
    
    /**
     * 查询所有数据
     */
	@Override
	public List<Menu> getAllMenus() {
		List<Menu> menus = (List<Menu>) menuRepository.findAll();
    	return menus;
	}

	/**
	 * 新增菜单
	 */
	@Override
	public Menu addMenu(MenuBo menuBo) {
		Menu menu = new Menu();
		BaseUtil.copyProperties(menuBo,menu);
		return menuRepository.save(menu);
	}

	@Override
	public MenuVo getMenu(String menuId) {
		return (MenuVo) BaseUtil.copyProperties(menuRepository.findMenuByMenuId(menuId), new MenuVo());
	}

	@Override
	public Menu updateMenu(MenuBo menuBo) {
		Menu menu = menuRepository.findMenuByMenuId(menuBo.getMenuId());
		if(menu!=null) {
			BaseUtil.copyNotNullProperties(menuBo,menu);
			return menuRepository.save(menu);
		}
		return null;
	}
    
    




}
