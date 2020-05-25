package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.bo.RoleBo;
import com.bean.po.Role;
import com.bean.vo.RoleVo;
import com.common.Result;
import com.service.RoleService;
import com.util.BaseUtil;
import com.util.CreateId;

@RequestMapping("role")
@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	/**
	 *	 获取全部角色
	 * @return
	 */
	@GetMapping("getRoles")
	public Object getAllRoles() {
		List<RoleVo> roles = roleService.getAllRoles();
		return Result.success(roles);
	}
	
	/**
	 * 	修改角色和角色权限
	 */
	@PostMapping("updateRole")
	public Object update(@RequestBody RoleBo role) {
		if(role!=null) {
			RoleVo roleVo= roleService.findRoleById(role.getRoleId());
			String[] menuIds = roleService.getMenuIds(role.getRoleId());
			if(roleVo!=null) {
				role.setUpdateTime(new Date());
//				if(BaseUtil.isSuccess(roleService.update(role))) {
//		    		return Result.success();
//		    	}
				String[] menuIds1 = role.getMenuIds();
				List<String> insertList = new ArrayList<String>();
				List<String> deleteList = new ArrayList<String>();
				//判断是新增权限还是删除权限
				if((menuIds==null || menuIds.length==0) && menuIds1!=null && menuIds1.length>0) {
					for(String menuId:role.getMenuIds()) {
						insertList.add(menuId);
					}
				}else if(menuIds!=null && menuIds.length>0 && (menuIds1==null || menuIds1.length==0)) {
					for(String menuId:menuIds) {
						deleteList.add(menuId);
					}
				}else if(menuIds!=null && menuIds.length>0 && menuIds1!=null && menuIds1.length>0){
					List<String> diffIds = BaseUtil.getDiffrent(Arrays.asList(menuIds), Arrays.asList(menuIds1));
					for(int i=0;i<diffIds.size();i++) {
						for(int j=0;j<menuIds.length;j++) {
							if(diffIds.get(i).equals(menuIds[j])) {
								deleteList.add(diffIds.get(i));
								break;
							}else if(j==menuIds.length-1) {
								insertList.add(diffIds.get(i));
							}
						}
					}
				}
				
				if(deleteList.size()>0) {
					roleService.deletePermission(deleteList, roleVo.getRoleId());
				}else if(insertList.size()>0){
					roleService.insertPermission(insertList, roleVo.getRoleId());
				}
			}
		}
        return Result.failed();
	}
	
	@PostMapping("addRole")
	public Object addRole(@RequestBody RoleBo role){
		if(role!=null) {
			try {
				role.setRoleId(CreateId.getid());
				role.setDeleteFlag((short)0);
				Role rolePo = roleService.addRole(role);
				if(rolePo!=null && role.getMenuIds()!=null && role.getMenuIds().length>0) {
					List<String> insertList = new ArrayList<String>();
					for(String menuId:role.getMenuIds()) {
						insertList.add(menuId);
					}
					roleService.insertPermission(insertList, rolePo.getRoleId());
				}
				return Result.failed();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Result.failed();
	}
	
	@PostMapping("delete")
	public Object delete(@RequestParam("id")String id) {
		if(id!=null) {
			if(BaseUtil.isSuccess(roleService.deleteRoleById(id))) {
	    		return Result.success();
	    	}
		}
		return Result.failed();
	}

}
