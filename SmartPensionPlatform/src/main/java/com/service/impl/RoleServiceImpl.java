package com.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.bean.po.Role;
import com.bean.po.RoleRepository;
import com.bean.vo.RoleVo;
import com.service.RoleService;

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
    
    
    
    private RoleVo getVo(Role po) {
    	RoleVo vo = new RoleVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}


}
