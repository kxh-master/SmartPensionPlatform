package com.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.entity.User;

public interface UserService {
	
	User findByUserName(String userName);
	
	User save(User user);
	
	List<User> findAll();
	
	Integer delete(int userId);
	
	Integer update(User user);
	
//	Page<User> findBookCriteria(Integer page,Integer size,UserQuery userQuery);
	
	Page<User> findByCondition(User user, Pageable pageable);

}
