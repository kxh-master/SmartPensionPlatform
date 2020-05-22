package com.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.vo.MenuVo;
import com.common.Result;
import com.service.MenuService;
import com.util.PackageUtil;

@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;	
	
	@GetMapping("getMenus")
	public Object getAllMenus(@RequestParam("roleId") String roleId) {
		List<MenuVo> menuList = new ArrayList<MenuVo>();
		if(roleId!=null && !"undefined".equals(roleId)) {
			Set<String> roleIdSet = new HashSet<String>();
			roleIdSet.add(roleId);
			menuList = menuService.getMenusByRoleId(roleIdSet);
		}else {
			menuList = menuService.getAllMenus();
		}
		//封装成前台需要的格式
		List<MenuVo> menuVoList = new ArrayList<MenuVo>();
		menuVoList =PackageUtil.packageMenuList(menuVoList,menuList,"0",null);
		return Result.success(menuVoList);
	}
}
