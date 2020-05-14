package com.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bean.bo.UserBo;
import com.bean.po.Menu;
import com.bean.po.Role;
import com.bean.po.User;
import com.bean.po.UserRepository;
import com.bean.vo.MenuVo;
import com.bean.vo.RoleVo;
import com.bean.vo.UserVo;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Resource
    private UserRepository userRepository;
	
    @Override
    public UserVo findByUserName(String userName) {
    	User user = userRepository.findByUserName(userName);
        return getVo(user);
    }
    
    @Transactional(rollbackFor = Exception.class)
    public UserVo save(UserBo userbo) {
    	User user = getPo(userbo);
    	user =userRepository.save(user);;
    	UserVo userVo = getVo(user);
        return userVo;
    }

	@Override
	public List<UserVo> findAll() {
		List<User> userList = (List<User>) userRepository.findAll();
		List<UserVo> userVoList = new ArrayList<UserVo>();
		if(userList!=null && userList.size()>0) {
			for(User user:userList) {
				UserVo userVo = getVo(user);
				userVoList.add(userVo);
			}
		}
        return userVoList;
	}

	@Transactional(rollbackFor = Exception.class)
	public Integer delete(int userId) {
		return userRepository.delete(userId);
	}

	
	public Integer update(UserBo user) {
		return userRepository.update(getPo(user));
	}
	
	public UserVo findUserByName(String name) {
		User user = userRepository.findUserByName(name);
		UserVo userVo = new UserVo();
		if(user!=null) {
			BeanUtils.copyProperties(user,userVo);
		}
		return userVo;
	}
	
	//分页查询 + 动态条件 + nativeQuery = true
    public Page<User> findPageNativeQuery(Integer age, Pageable pageable) {
        return userRepository.findPageNativeQuery(age,pageable);
    }


    public Page<UserVo> findByCondition(UserBo user, Pageable pageable) {
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
    
    private User getPo(UserBo bo) {
		User po = new User();
		BeanUtils.copyProperties(bo, po);
		return po;
	}

	private UserVo getVo(User po) {
		UserVo vo = new UserVo();
		BeanUtils.copyProperties(po, vo);
		return vo;
	}


}
