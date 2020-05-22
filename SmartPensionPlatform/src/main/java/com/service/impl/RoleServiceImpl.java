package com.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.bo.RoleBo;
import com.bean.po.Role;
import com.bean.po.RoleRepository;
import com.bean.vo.RoleVo;
import com.service.RoleService;
import com.util.BaseUtil;

@Service
public class RoleServiceImpl implements RoleService{

	@Resource
    private RoleRepository roleRepository;
	
	/**
	 *	根据客户id获取角色
	 */
    @Override
    public Set<RoleVo> getRolesByUserId(String userId) {
    	Set<RoleVo> roleVos = new HashSet<RoleVo>();
    	Set<Role> roles = roleRepository.getRolesByUserId(userId);
    	if(roles!=null && roles.size()>0) {
    		for(Role role:roles) {
    			roleVos.add(getVo(role));
    		}
    	}
        return roleVos;
    }
    
    @Override
	public List<RoleVo> getAllRoles() {
    	List<RoleVo> roleVos = new ArrayList<RoleVo>();
    	List<Role> roles = (List<Role>) roleRepository.findAll();
    	if(roles!=null && roles.size()>0) {
    		for(Role role:roles) {
    			roleVos.add(getVo(role));
    		}
    	}
		return roleVos;
	}
    
    
    private RoleVo getVo(Role po) {
    	RoleVo vo = new RoleVo();
		BaseUtil.copyProperties(po, vo);
		return vo;
	}

	@Override
	public RoleVo findRoleById(String roleId) {
		Role bo = roleRepository.queryByRoleid(roleId);
		return (RoleVo) BaseUtil.copyProperties(bo,new RoleVo());
	}

	public String[] getMenuIds(String roleId){
		String[] menuIds = roleRepository.getMenuIds(roleId);
		return menuIds;
	}
	
	@Override
	public Integer update(RoleBo roleBo) {
		Integer isSuccess = roleRepository.update((Role) BaseUtil.copyProperties(roleBo,new Role()));
		return isSuccess;
	}
	
	/**
	 * 	添加权限
	 */
	public void insertPermission(List<String> menuIds,String roleId) {
		for(String menuId:menuIds) {
			roleRepository.insertPermission(roleId,menuId);
		}
		
	}
	
	/**
	 * 	删除权限
	 */
	public void deletePermission(List<String> menuIds ,String roleId) {
		roleRepository.deletePermission(roleId,menuIds);
	}


}
