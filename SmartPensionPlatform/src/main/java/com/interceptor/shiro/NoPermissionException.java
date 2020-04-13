package com.interceptor.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
	 */
    @ExceptionHandler(UnauthorizedException.class)
    public String handleShiroException(Exception ex) {
        return "redirect:/unauthc";
    }
    
    /**
         * 捕获未登录异常
     * @param ex
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public String AuthorizationException(Exception ex) {
        return "redirect:/toLogin";
    }
}
