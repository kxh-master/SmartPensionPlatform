package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bean.bo.UserBo;
import com.bean.vo.MenuVo;
import com.bean.vo.RoleVo;
import com.bean.vo.UserVo;
import com.common.Result;
import com.common.ResultCode;
import com.service.MenuService;
import com.service.RoleService;
import com.service.UserService;
import com.util.BaseUtil;
import com.util.PackageUtil;
import com.util.RedisUtil;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	
	@GetMapping("info")
    public Object index(@RequestParam("userName") String userName) {
		Map<String,Object> map =new HashMap<String,Object>();
        if(userName!=null){
        	UserVo userVo = userService.findByUserName(userName);
        	if(userVo==null) {
        		return ResultCode.USER_NOT_FOUND;
        	}
        	//获取用户角色
            Set<RoleVo> roleList = roleService.getRolesByUserId(userVo.getUserId().toString());
            Set<String> roleIds = new HashSet<>(roleList.size());
            if(roleList!=null && !roleList.isEmpty()) {
            	for (RoleVo role : roleList) {
                    roleIds.add(role.getRoleId());
                }
            	//存入redis缓存
            	redisUtil.set("roleList", roleList);
            }
            //获取菜单以及权限
            List<MenuVo> menuList = menuService.getMenusByRoleId(roleIds);
            if(menuList!=null && !menuList.isEmpty()) {
            	redisUtil.set("menuList", menuList);
            	List<MenuVo> menuVoList = new ArrayList<MenuVo>();
            	//封装成前台需要的格式
            	menuVoList =PackageUtil.packageMenuList(menuVoList,menuList,"0",null);
            	map.put("menus", menuVoList);
            }
            map.put("user", userVo);
        }
        return Result.success(map);
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
    public Object add(UserBo user){
		UserVo users = userService.save(user);
		if(users!=null) {
			return Result.success();
		}
		return Result.failed();
    }
    
    /**
         * 修改用户
     * @return
     */
    @GetMapping("edit")
    @RequiresPermissions("user:edit")//权限管理;
    public Object edit(UserBo user) {
    	if(BaseUtil.isSuccess(userService.update(user))) {
    		return Result.success();
    	}
        return Result.failed();
    }

    /**
         * 删除用户
     * @return
     */
    @RequestMapping("delete")
    @RequiresPermissions("user:delete")
    public Object delete(@RequestParam("userId")Integer userId) {
    	if(BaseUtil.isSuccess(userService.delete(userId))) {
    		return Result.success();
    	}
    	return Result.failed();
    }
}
