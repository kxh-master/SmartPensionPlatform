package com.util;

import java.util.ArrayList;
import java.util.List;

import com.bean.vo.MenuVo;

public class PackageUtil {
	/**
     * 	封装菜单权限数据
     * @param menuList
     * @param menuVoList
     * @return
     */
    public static List<MenuVo> packageMenuList(List<MenuVo> menuList,List<MenuVo> menuVoList,String parentId,MenuVo menuVo){
		List<MenuVo> childrenList = new ArrayList<MenuVo>();
        for (MenuVo menuVo1:menuVoList){
        	if(parentId.equals(menuVo1.getParentId().toString())) {
        		//判断是菜单还是按钮
        		if(menuVo1.getMenuType()==1) {
    				menuVo1.setHidden(false);
    			}else if(menuVo1.getMenuType()==2) {
    				menuVo1.setHidden(false);
    			}else if(menuVo1.getMenuType()==3) {
    				menuVo1.setHidden(true);
    			}
        		if("0".equals(parentId)) {
        			menuList.add(menuVo1);
                    packageMenuList(menuList,menuVoList,menuVo1.getMenuId().toString(),menuVo1);
        		}else {
        			if(menuVo1.getMenuUrl()!=null) {
        				menuVo1.setMenuUrl(menuVo1.getMenuUrl());
        			}
    				childrenList.add(menuVo1);
    				packageMenuList(menuList,menuVoList,menuVo1.getMenuId().toString(),menuVo1);
        		}
            }
        }
        if(menuVo!=null && !childrenList.isEmpty()) {
        	menuVo.setChildren(childrenList);
        }
		return menuList;
    }
}
