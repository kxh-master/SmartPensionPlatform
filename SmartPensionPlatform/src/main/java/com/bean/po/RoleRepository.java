package com.bean.po;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bean.po.base.BaseRepository;

@Transactional(rollbackFor = Exception.class)//事务
public interface RoleRepository extends BaseRepository<Role,Long>{
	
	@Query(value="select * from sys_role where role_id = ?1",nativeQuery=true)
	Role queryByRoleid(String roleid);
	
	@Query("update Role set roleName  = ?1 where roleId  = ?2")
	@Modifying //需要执行一个更新操作
	void updateById(String rolename,Integer roleid);
	
	@Query(value="select * from sys_role r where r.delete_flag=0 and r.role_id in (select sur.role_id from sys_user_role sur where sur.user_id = ?1)",nativeQuery=true)
	Set<Role> getRolesByUserId(String userId);
	
	@Modifying //指明这是一个更新操作
	@Query("update Role role set " +
            "role.roleName = CASE WHEN :#{#role.roleName} IS NULL THEN role.roleName ELSE :#{#role.roleName} END ," +
            "role.roleAlias = CASE WHEN :#{#role.roleAlias} IS NULL THEN role.roleAlias ELSE :#{#urole.roleAlias} END ," +
            "role.updateTime =  CASE WHEN :#{#role.updateTime} IS NULL THEN role.updateTime ELSE :#{#role.updateTime} END " +
            "where role.roleId = :#{#role.roleId}")
	Integer update(@Param("role")Role role);
	
	@Query(value="select menu_id from sys_role_menu where role_id = ?1",nativeQuery=true)
	String[] getMenuIds(String roleId);
	
	@Query(value="insert into sys_role_menu values(?1,?2)",nativeQuery=true)
	@Modifying
	void insertPermission(String roleId,String menuId);
	
	@Query(value="delete from sys_role_menu where role_id = ?1 and menu_id in(?2)",nativeQuery=true)
	@Modifying
	void deletePermission(String roleId,List<String> menuId);
	
}
