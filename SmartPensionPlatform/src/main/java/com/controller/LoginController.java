package com.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.CommonResult;
import com.entity.User;
import com.service.UserService;
import com.util.PasswordUtil;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
    /**
     * 执行登录操作
     * @param userName
     * @param passWord
     * @return
     */
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public Object doLogin(@RequestBody User user) {
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassWord());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) { 
        	//用户名或者密码错误
	    	return CommonResult.validateFailed();
	    }
        user = userService.findByUserName(user.getUserName());
        subject.getSession().setAttribute("user", user);
        return CommonResult.success(user.getName());
    }
    
    @RequestMapping("toLogin")
    public Object toLogin() {
    	return "请重新登录";
    }
    
    @GetMapping("unauthc")
    public Object unauthc() {
        return "您没有该资源的访问权限";
    }

    /**
         * 注册用户
     * @param userName
     * @param passWord
     * @return
     */
    @GetMapping("register")
    public Object register(@RequestParam String userName, @RequestParam String passWord) {
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        passwordUtil.encryptPassword(user);

        userService.save(user);
        return "SUCCESS";
    }
    
}
