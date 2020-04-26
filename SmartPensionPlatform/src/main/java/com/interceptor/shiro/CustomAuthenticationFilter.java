package com.interceptor.shiro;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.alibaba.fastjson.JSON;

public class CustomAuthenticationFilter extends FormAuthenticationFilter{
	
	public CustomAuthenticationFilter() {
        super();
    }
	
    @Override
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	if (request instanceof HttpServletRequest) {
    		//直接通过options请求
    		if (((HttpServletRequest) request).getMethod().toUpperCase().equals("OPTIONS")) {
                return true;
            }
    		System.out.println("path:------"+((HttpServletRequest) request).getMethod());
    		System.out.println("path:------"+((HttpServletRequest) request).getServletPath());
        }
    	return super.isAccessAllowed(request, response, mappedValue);
    }

    /**
     * 未登录
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse)response;
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        Map<String, Object> map= new HashMap<>();
        map.put("code", 702);
        map.put("msg", "未登录");
        writer.write(JSON.toJSONString(map));
        writer.close();
        return false;
    }
    

}
