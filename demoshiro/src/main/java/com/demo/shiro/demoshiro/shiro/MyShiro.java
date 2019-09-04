package com.demo.shiro.demoshiro.shiro;

import com.demo.shiro.demoshiro.controller.LoginController;
import com.demo.shiro.demoshiro.dao.PermissionDao;
import com.demo.shiro.demoshiro.dao.RoleDao;
import com.demo.shiro.demoshiro.dao.UserDao;
import com.demo.shiro.demoshiro.models.Permission;
import com.demo.shiro.demoshiro.models.Role;
import com.demo.shiro.demoshiro.models.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyShiro extends AuthorizingRealm {
    Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName =  SecurityUtils.getSubject().getPrincipal().toString();
        System.out.println("用户" + userName + "获取权限-----ShiroRealm.doGetAuthorizationInfo");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集
        List<Role> roleList = roleDao.getUserRole(userName);
        Set<String> roleSet = new HashSet<String>();
        for (Role r : roleList) {
            roleSet.add(r.getName());
        }
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<Permission> permissionList = permissionDao.getUserPermission(userName);
        Set<String> permissionSet = new HashSet<String>();
        for (Permission p : permissionList) {
            permissionSet.add(p.getName());
        }
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        // 从数据库获取对应用户名的用户
        UserEntity user = userDao.getUserEntitiesByName(username);
        log.info("password :" + DigestUtils.md5DigestAsHex(password.getBytes()));
        log.info("name :" + super.getName());
        if (user == null) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!user.getPassword().equals(md5Password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), getName());

        return simpleAuthenticationInfo;
    }
}
