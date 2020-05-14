package com.service;

import java.util.Set;

import com.bean.vo.RoleVo;

public interface RoleService {
	
	 public Set<RoleVo> getRolesByUserId(String userId);

}
