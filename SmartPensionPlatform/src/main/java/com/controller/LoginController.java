package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.common.CommonResult;
import com.entity.Person;
import com.entity.PersonRepository;
import com.entity.User;
import com.service.UserService;
import com.util.PasswordUtil;

@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Autowired
	private PersonRepository personService;
	
	/**
	  * 进入登录页面
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "toLogin", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user) {
        if (user.getUserName().equals("admin") && user.getPassWord().equals("123456"))
            return CommonResult.success("admin");
        else
            return CommonResult.validateFailed();
    }

    /**
     * 执行登录操作
     * @param userName
     * @param passWord
     * @return
     */
    @GetMapping("login")
    public Object doLogin(@RequestParam String userName, @RequestParam String passWord) {
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) { 
	    	return "身份验证失败！";
	    }
        User user = userService.findByUserName(userName);
        subject.getSession().setAttribute("user", user);
        subject.getSession().setAttribute("user1", 123);
        return "SUCCESS";
    }
    
    @RequestMapping("toLogin")
    public Object toLogin() {
    	return "请重新登录";
    }
    
    @GetMapping("unauthc")
    public Object unauthc() {
        return "您没有该资源的访问权限";
    }

    @GetMapping("register")
    public Object register(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUserName(username);
        user.setPassWord(password);
        passwordUtil.encryptPassword(user);

        userService.save(user);
        return "SUCCESS";
    }
    
    @ResponseBody
    @GetMapping("findAll")
    public Object getPersionList() {
    	
    	List<Person> personList =(List<Person>) personService.findAll();

        return personList;
    }
}
