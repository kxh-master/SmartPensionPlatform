package com;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bean.bo.UserBo;
import com.bean.po.RoleRepository;
import com.bean.po.UserRepository;
import com.util.PasswordUtil;



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
	@Autowired
	private PasswordUtil passwordUtil;
	
	/**
	 * 一对多关联关系的添加
	 */
	@Test
	public void testSave(){
		//创建一个用户
		UserBo users = new UserBo();
		users.setAddress("深圳");
		users.setUserName("kxh");
		users.setAddTime(new Date());
		users.setAge(27);
		users.setDeleteFlag((short)0);
		users.setName("小柯");
		users.setPassWord("123456");
		passwordUtil.encryptPassword(users);
		
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
//		User user1 = usersRepository.findUserByName("admin");
//		System.out.println(user1);
//		
//		User user2 = usersRepository.findByUserName("admin");
//		System.out.println(user2);
//		
//		
//		Integer age = 22;
//		Pageable pageable = PageRequest.of(0,3, Sort.Direction.DESC,"add_time");
//		Page<User> list = usersRepository.findPageNativeQuery(age,pageable);
//		if(list!=null && list.getContent().size()>0) {
//			List<User> userList = list.getContent();
//			for(User user:userList) {
//				Set<Role> roleList = user.getRoles();
//				System.out.println(roleList.size());
//			}
//		}
//		System.out.println(list.getContent().size());
	}


}
