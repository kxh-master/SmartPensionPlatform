package com.entity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.base.BaseRepository;

@Repository
@Transactional(rollbackFor = Exception.class)//事务
public interface UserRepository extends BaseRepository<User, Integer>{
	
	//分页查询 + 动态条件 + nativeQuery = true
    @Query(value = "select * from sys_user s where s.age=?1 order by ?#{#pageable}",nativeQuery = true)
    Page<User> findPageNativeQuery(Integer age, Pageable pageable);
	
	@Query("from User where name = ?1")
	List<User> queryByNameUseHQL(String name);
	/**
	 * nativeQuery=true，指定这是一条原生sql,即可以在数据库运行的，如果不指定，
	 * jpa会先将不标准的sql转化为标准的sql语句执行，相对内部操作过程会多一步骤
	 * @param name
	 * @return
	 */
	@Query(value="select * from sys_user where user_name = ?1",nativeQuery=true)
	User findUserByName(String name);
	
	User findByUserName(String userName);
	
	@Modifying
	@Query("update User set deleteFlag  = 1 where userId  = ?1")
	Integer delete(int id);
	
	
	@Modifying //指明这是一个更新操作
	@Query("update User user set " +
            "user.userName = CASE WHEN :#{#user.userName} IS NULL THEN user.userName ELSE :#{#user.userName} END ," +
            "user.name = CASE WHEN :#{#user.name} IS NULL THEN user.name ELSE :#{#user.name} END ," +
            "user.passWord = CASE WHEN :#{#user.passWord} IS NULL THEN user.passWord ELSE :#{#user.passWord} END ," +
            "user.age =  CASE WHEN :#{#user.age} IS NULL THEN user.age ELSE :#{#user.age} END, " +
            "user.deleteFlag =  CASE WHEN :#{#user.deleteFlag} IS NULL THEN user.deleteFlag ELSE :#{#user.deleteFlag} END, " +
            "user.salt =  CASE WHEN :#{#user.salt} IS NULL THEN user.salt ELSE :#{#user.salt} END, " +
            "user.updateTime =  CASE WHEN :#{#user.updateTime} IS NULL THEN user.updateTime ELSE :#{#user.updateTime} END " +
            "where user.userId = :#{#user.userId}")
	Integer update(@Param("user") User us);
	
}
