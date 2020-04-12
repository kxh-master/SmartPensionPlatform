package com.entity;

import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.base.BaseRepository;

public interface RoleRepository extends BaseRepository<Role,Long>{
	
	@Query(value="select * from sys_role where role_id = ?1",nativeQuery=true)
	Set<Role> queryByRoleid(String roleid);
	
	@Query("update Role set roleName  = ?0 where roleId  = ?1")
	@Modifying //需要执行一个更新操作
	void updateById(String rolename,Integer roleid);
	
	@Query(value="select * from sys_role where role_id in (select sur.role_id from sys_user_role sur where sur.user_id = ?1)",nativeQuery=true)
	Set<Role> findRolesByUserId(String userId);
}
