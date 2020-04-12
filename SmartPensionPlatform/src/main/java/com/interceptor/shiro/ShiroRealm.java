package com.interceptor.shiro;


import java.util.HashSet;
import java.util.Set;

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

import com.entity.Menu;
import com.entity.MenuRepository;
import com.entity.Role;
import com.entity.RoleRepository;
import com.entity.User;
import com.entity.UserRepository;


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

	/**
	 * 	获取权限信息
	 */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String userId =principals.getPrimaryPrincipal().toString();
        //获取角色
        Set<Role> roleList = roleRepository.findRolesByUserId(userId);
        Set<String> roleIds = new HashSet<>(roleList.size());
        for (Role role : roleList) {
            authorizationInfo.addRole(role.getRoleAlias());
            roleIds.add(role.getRoleId());
        }
        //获取权限
        Set<Menu> menuList = menuRepository.findMenusByRoleId(roleIds);
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
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUserId(), user.getPassWord(),
                ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
        return authenticationInfo;
    }

}
