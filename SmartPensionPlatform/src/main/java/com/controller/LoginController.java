package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.common.Result;
import com.entity.User;
import com.service.UserService;
import com.util.PasswordUtil;
import com.vo.UserVO;

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
	    	return Result.validateFailed();
	    }
        Map<String,Object> map =new HashMap<String,Object>();
        user = userService.findUserByName(user.getUserName());
        UserVO userVO = new UserVO();
        if(user!=null) {
        	BeanUtils.copyProperties(user, userVO);
        	subject.getSession().setAttribute("user", userVO);
        }
        map.put("token",subject.getSession().getId());
        map.put("user",userVO);
        return Result.success(map);
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
