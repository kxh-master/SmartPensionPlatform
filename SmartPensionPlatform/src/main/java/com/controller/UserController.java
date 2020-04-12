package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

import io.lettuce.core.GeoArgs.Sort;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("index")
    public Object index() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        return user.toString();
    }
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
//	@RequestMapping("list")
//	@RequiresPermissions("user:list")
//	public Object list(Integer pageNo, Integer pageSize,Integer age) {
//		
//	}
	
	/**
	  * 添加用户
	 * @return
	 */
    @GetMapping("admin")
    @RequiresPermissions("user:add")//权限管理;
    public Object add(@RequestBody User user) {
        return userService.save(user);
    }
    
    /**
         * 修改用户
     * @return
     */
    @GetMapping("edit")
    @RequiresPermissions("user:edit")//权限管理;
    public Object edit(User user) {
    	Integer result = userService.update(user);
    	boolean isSuccess=false;
		if(result>0) {
			isSuccess = true;
		}
        return isSuccess;
    }

    /**
         * 删除用户
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions("user:delete")
    public Object delete(@RequestParam("userId")Integer userId) {
    	Integer result = userService.delete(userId);
    	boolean isSuccess=false;
		if(result>0) {
			isSuccess = true;
		}
        return isSuccess;
    }

}
