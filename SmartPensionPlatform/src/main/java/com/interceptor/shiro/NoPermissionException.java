package com.interceptor.shiro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;

/**
  * 捕获shiro全局异常
 * @author kxh
 *
 */
@ControllerAdvice
public class NoPermissionException {
    /**
	  * 捕获认证未通过异常
	 * @param ex
	 * @return
     * @throws IOException 
	 */
   @ExceptionHandler(UnauthorizedException.class)
   public boolean handleShiroException(ServletRequest request, ServletResponse response) throws IOException {
	   HttpServletResponse res = (HttpServletResponse)response;
       res.setHeader("Access-Control-Allow-Origin", "*");
       res.setStatus(HttpServletResponse.SC_OK);
       res.setCharacterEncoding("UTF-8");
       PrintWriter writer = res.getWriter();
       Map<String, Object> map= new HashMap<>();
       map.put("code", 703);
       map.put("msg", "没有该资源的访问权限");
       writer.write(JSON.toJSONString(map));
       writer.close();
       return false;
   }
    
    /**
     * 捕获未登录异常
     * @param ex
     * @return
     */
//    @ExceptionHandler(AuthorizationException.class)
//    public String AuthorizationException(Exception ex) {
//        return "redirect:/toLogin";
//    }
}
