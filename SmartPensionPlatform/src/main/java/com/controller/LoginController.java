package com.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user) {
        if (user.getUserName().equals("admin") && user.getPassWord().equals("123456"))
            return CommonResult.success("admin");
        else
            return CommonResult.validateFailed();
    }

    @GetMapping("unauthc")
    public Object unauthc() {
        return "Here is Unauthc page";
    }
    
    @RequestMapping("getList")
    @ResponseBody
    public List<User> getList(){
    	List<User> users = userService.findAll();
    	return users;
    }

    @GetMapping("doLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.getSubject().getSession().setTimeout(1000*30);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (IncorrectCredentialsException ice) {
            return "password error!";
        } catch (UnknownAccountException uae) {
            return "username error!";
        }

        User user = userService.findByUserName(username);
        subject.getSession().setAttribute("user", user);
        return "SUCCESS";
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
