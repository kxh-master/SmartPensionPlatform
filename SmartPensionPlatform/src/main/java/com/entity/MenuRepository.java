package com.entity;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.base.BaseRepository;

public interface MenuRepository extends BaseRepository<Menu,Long>{
	@Query(value="select * from sys_menu where menusid = ?0",nativeQuery=true)
	List<Menu> queryByMenusid(String menusid);
	
	@Query("update Menu set menusName  = ?0 where menuId  = ?1")
	@Modifying //需要执行一个更新操作
	void updateMenuNameById(String menusname,Integer menusid);
	
	@Query(value="select * from sys_menu where menu_id in (select sm.menu_id from sys_role_menu sm where sm.role_id in(?1))",nativeQuery=true)
	Set<Menu> findMenusByRoleId(Set<String> roleIds); 
}
