package com.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.util.RedisUtil;

@RequestMapping("redis")
@RestController
public class RedisController {
	private static int ExpireTime = 60;   // redis中存储的过期时间60s

    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("set")
    public boolean redisset(String key, String value){
    	User user = new User();
    	user.setUserName("zhangsan");
    	user.setAddress("hubei");
    	user.setAddTime(new Date());
    	user.setAge(20);

        return redisUtil.set(key,user,ExpireTime);

//        return redisUtil.set(key,value);
    }

    @RequestMapping("get")
    public Object redisget(String key){
        return redisUtil.get(key);
    }

    @RequestMapping("expire")
    public boolean expire(String key){
        return redisUtil.expire(key,ExpireTime);
    }
}
