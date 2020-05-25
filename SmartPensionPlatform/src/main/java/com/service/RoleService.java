package com.service;

import java.util.List;
import java.util.Set;

import com.bean.bo.RoleBo;
import com.bean.po.Role;
import com.bean.vo.RoleVo;

public interface RoleService {
	
	 public Set<RoleVo> getRolesByUserId(String userId);

	 public List<RoleVo> getAllRoles();
	 
	 public RoleVo findRoleById(String roleId);
	 
	 public Integer update(RoleBo rolebo);
	 
	 public String[] getMenuIds(String roleId);
	 
	 /**
	  * 	添加权限
	  * @param menuIds
	  * @param roleId
	  */
	 public void insertPermission(List<String> menuIds,String roleId);
	 
	 /**
	  * 	删除权限
	  * @param menuIds
	  * @param roleId
	  */
	 public void deletePermission(List<String> menuIds ,String roleId);
	 
	 /**
	  * 新增角色
	  * @param roleBo
	  * @return
	  */
	 public Role addRole(RoleBo roleBo);
	 
	 /**
	  * 删除角色
	  */
	 public Integer deleteRoleById(String id);
	 
	 
}
