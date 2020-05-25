package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.bo.MenuBo;
import com.bean.po.Menu;
import com.bean.vo.MenuVo;
import com.bean.vo.TreeTable;
import com.common.Result;
import com.service.MenuService;
import com.util.CreateId;
import com.util.PackageUtil;

@RestController
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuService;	
	
	@GetMapping("getMenu")
	public Object getMenu(@RequestParam("id") String menuId) {
		if(menuId!=null && !"".equals(menuId)) {
			MenuVo menuVo = menuService.getMenu(menuId);
			if(menuVo!=null) {
				return Result.success(menuVo);
			}
		}
		return Result.failed();
	}
	
	/**
	 * 获取角色拥有的菜单权限
	 * @param roleId
	 * @return
	 */
	@GetMapping("getMenus")
	public Object getMenus(@RequestParam("roleId") String roleId) {
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		if(roleId!=null && !"".equals(roleId) && !"undefined".equals(roleId)) {
			Set<String> roleIdSet = new HashSet<String>();
			roleIdSet.add(roleId);
			menuList = menuService.getMenusByRoleId(roleIdSet);
		}else {
			menuList = menuService.getMenuList();
		}
		//封装成前台需要的格式
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
		menuVoList =PackageUtil.packageMenuList(menuVoList,menuList,"0",null);
		return Result.success(menuVoList);
	}
	
	/**
	 * 获取菜单列表
	 * @param roleId
	 * @return
	 */
	@GetMapping("getAllMenu")
	public Object getAllMenus() {
		List<Menu> menuList = menuService.getAllMenus();
		List<TreeTable> treeTableList = new ArrayList<TreeTable>();
		if(menuList!=null && !menuList.isEmpty()) {
			//封装成树形表格结构
			PackageUtil.packageMenuTableList(treeTableList,menuList,"0",null);
		}
		return Result.success(treeTableList);
	}
	/**
	 * 添加菜单
	 * @param menu
	 * @return
	 */
	@PostMapping("addMenu")
	public Object add(@RequestBody MenuBo menu) {
		if(menu!=null) {
			try {
				menu.setMenuId(CreateId.getid());
				menu.setDeleteFlag((short)0);
				menu.setAddTime(new Date());
				Menu menupo = menuService.addMenu(menu);
				if(menupo!=null) {
					return Result.success();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Result.failed();
	}
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	@PostMapping("updateMenu")
	public Object updatemMenu(@RequestBody MenuBo menu) {
		if(menu!=null) {
			try {
				Menu menupo = menuService.updateMenu(menu);
				if(menupo!=null) {
					return Result.success();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Result.failed();
	}
}
