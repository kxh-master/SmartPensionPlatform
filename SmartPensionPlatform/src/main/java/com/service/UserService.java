package com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bean.bo.UserBo;
import com.bean.po.User;
import com.bean.vo.UserVo;

public interface UserService {
	
	UserVo findByUserName(String userName);
	
	UserVo findUserByName(String userName);
	
	UserVo save(UserBo user);
	
	List<UserVo> findAll();
	
	Integer delete(int userId);
	
	Integer update(UserBo user);
	
//	Page<User> findBookCriteria(Integer page,Integer size,UserQuery userQuery);
	
	Page<UserVo> findByCondition(UserBo user, Pageable pageable);

}
