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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.CommonResult;
import com.entity.User;
import com.service.UserService;
import com.util.BaseUtil;

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
	@RequestMapping("list")
	@RequiresPermissions("user:list")
	public Object list(Integer pageNo, Integer pageSize,Integer age) {
		return true;
	}
	
	/**
	  * 添加用户
	 * @return
	 */
	@RequestMapping("add")
    @RequiresPermissions("user:add")//权限管理;
    public Object add(User user) {
		User users = userService.save(user);
		if(users!=null) {
			return CommonResult.success(users.getUserName());
		}
		return CommonResult.failed();
    }
    
    /**
         * 修改用户
     * @return
     */
    @GetMapping("edit")
    @RequiresPermissions("user:edit")//权限管理;
    public Object edit(User user) {
        return BaseUtil.isSuccess(userService.update(user));
    }

    /**
         * 删除用户
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions("user:delete")
    public Object delete(@RequestParam("userId")Integer userId) {
    	return BaseUtil.isSuccess(userService.delete(userId));
    }

}
