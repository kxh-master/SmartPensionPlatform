package com;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Role;
import com.entity.RoleRepository;
import com.entity.User;
import com.entity.UserRepository;



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTests {


	@Before
	public void setUp() {
	}
	
	@Autowired
	private UserRepository usersRepository;
	@Autowired
	private RoleRepository rolesRepository;
	
	/**
	 * 一对多关联关系的添加
	 */
	@Test
	public void testSave(){
		//创建一个用户
//		User users = new User();
//		users.setAddress("北京");
//		users.setUserName("小李子");
//		users.setUpdateTime(new Date());
//		users.setAge(21);
//		users.setDeleteFlag((short)0);
//		users.setName("小柯");
		
		//创建一个角色
//		Role roles = new Role();
//		roles.setRoleName("管理员");
//		roles.setAddTime(new Date());
//		
//		Set<Role> roleList = new HashSet<Role>();
//		roleList.add(roles);
//		
//		//关联
//		roles.getUsers().add(users);
//		rolesRepository.save(roles);
//		
//		users.getRoles().add(roles);
		
		//保存
//		usersRepository.save(users);
		User user1 = usersRepository.findUserByName("admin");
		System.out.println(user1);
		
		User user2 = usersRepository.findByUserName("admin");
		System.out.println(user2);
		
		
		Integer age = 22;
		Pageable pageable = PageRequest.of(0,3, Sort.Direction.DESC,"add_time");
		Page<User> list = usersRepository.findPageNativeQuery(age,pageable);
		if(list!=null && list.getContent().size()>0) {
			List<User> userList = list.getContent();
			for(User user:userList) {
				Set<Role> roleList = user.getRoles();
				System.out.println(roleList.size());
			}
		}
		System.out.println(list.getContent().size());
	}


}
