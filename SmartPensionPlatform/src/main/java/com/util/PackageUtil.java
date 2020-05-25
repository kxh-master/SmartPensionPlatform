package com.util;

import java.util.ArrayList;
import java.util.List;

import com.bean.po.Menu;
import com.bean.vo.MenuVo;
import com.bean.vo.TreeTable;

public class PackageUtil {
	/**
     * 	封装成树形结构
     * @param menuList
     * @param menuVoList
     * @return
     */
    public static List<MenuVo> packageMenuList(List<MenuVo> menuList,List<MenuVo> menuVoList,String parentId,MenuVo menuVo){
		List<MenuVo> childrenList = new ArrayList<MenuVo>();
        for (MenuVo menuVo1:menuVoList){
        	if(parentId.equals(menuVo1.getParentId())) {
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
    
    /**
     * 	封装成菜单列表树形表格结构
     * @param menuList
     * @param menuVoList
     * @return
     */
    public static List<TreeTable> packageMenuTableList(List<TreeTable> treeTableList,List<Menu> menuList,String parentId,TreeTable treeTable){
		List<TreeTable> childrenList = new ArrayList<TreeTable>();
        for (Menu menu:menuList){
        	if(parentId.equals(menu.getParentId().toString())) {
        		TreeTable tb = new TreeTable();
        		tb.setId(menu.getMenuId().toString());
        		tb.setParent_id(menu.getParentId().toString());
        		tb.setIcon(menu.getIcon());
        		tb.setName(menu.getMenuName());
        		tb.setUrl(menu.getMenuUrl());
        		tb.setOpen(false);
        		tb.setOrder(menu.getSortno());
        		tb.setAddTime(menu.getAddTime());
        		tb.setUpdateTime(menu.getUpdateTime());
        		tb.setType(menu.getMenuType());
        		tb.setDeleteFlag(menu.getDeleteFlag());
        		if("0".equals(parentId)) {
        			treeTableList.add(tb);
        			packageMenuTableList(treeTableList,menuList,menu.getMenuId().toString(),tb);
        		}else {
        			if(menu.getMenuUrl()!=null) {
        				menu.setMenuUrl(menu.getMenuUrl());
        			}
    				childrenList.add(tb);
    				packageMenuTableList(treeTableList,menuList,menu.getMenuId().toString(),tb);
        		}
            }
        }
        if(treeTable!=null && !childrenList.isEmpty()) {
        	treeTable.setLists(childrenList);
        }
		return treeTableList;
    }
}
