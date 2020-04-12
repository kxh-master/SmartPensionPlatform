package com.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.entity.User;
import com.entity.UserRepository;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
    private UserRepository userRepository;
	
    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
    	return userRepository.save(user);
    }

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer delete(int userId) {
		return userRepository.delete(userId);
	}

	
	public Integer update(User user) {
		return userRepository.update(user);
		
	}
	
	//分页查询 + 动态条件 + nativeQuery = true
    public Page<User> findPageNativeQuery(Integer age, Pageable pageable) {
        return userRepository.findPageNativeQuery(age,pageable);
    }


    public Page<User> findByCondition(User user, Pageable pageable) {
//		 return userRepository.findAll((root, query, cb) -> {
//	            List<Predicate> predicates = new ArrayList<>();
//	            //equal 示例
//	            if (!StringUtils.isNullOrEmpty(user.getIntroduction())){
//	                predicates.add(cb.equal(root.get("introduction"),detailParam.getIntroduction()));
//	            }
//	            //like 示例
//	            if (!StringUtils.isNullOrEmpty(user.getRealName())){
//	                predicates.add(cb.like(root.get("realName"),"%"+detailParam.getRealName()+"%"));
//	            }
//	            //between 示例
////	            if (user.getMinAge()!=null && user.getMaxAge()!=null) {
//	                Predicate agePredicate = cb.between(root.get("age"), detailParam.getMinAge(), detailParam.getMaxAge());
//	                predicates.add(agePredicate);
//	            }
//	            return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
//	        }, pageable);
    	return null;
	}

}
