package com.interceptor.shiro;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.bean.po.Menu;
import com.bean.po.MenuRepository;
import com.bean.po.Role;
import com.bean.po.RoleRepository;
import com.bean.po.User;
import com.bean.po.UserRepository;
import com.util.RedisUtil;


/**
 * @author kxh
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MenuRepository menuRepository;
	
	@Resource
    private RedisUtil redisUtil;

	/**
	 * 	获取权限信息
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user =(User) principals.getPrimaryPrincipal();
        //获取角色
        Set<Role> roleList = roleRepository.getRolesByUserId(user.getUserId().toString());
        Set<String> roleIds = new HashSet<>(roleList.size());
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleAlias());
            roleIds.add(role.getRoleId());
        }
        //获取权限
        List<Menu> menuList = menuRepository.getMenusByRoleId(roleIds);
        //将菜单放入缓存
        redisUtil.set("menuList",menuList);
        for (Menu menu: menuList) {
        	if(menu.getPermission()!=null) {
        		authorizationInfo.addStringPermission(menu.getPermission());
        	}
        }
        return authorizationInfo;
    }

    /*
     * 	身份认证:验证用户输入的账号和密码是否正确
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userRepository.findUserByName(username);

        if (user == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, user.getPassWord(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

}
