package com.demo.shiro.demoshiro.controller;

import com.demo.shiro.demoshiro.dao.UserDao;
import com.demo.shiro.demoshiro.models.UserEntity;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sun.security.provider.MD5;

import java.util.List;

@RestController
public class LoginController {
    Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/login")
    public String  login(@RequestParam String name,@RequestParam String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return  "fail";
        }
        return "success";
    }

    @GetMapping(value = "/test")
    @RequiresPermissions("user:test")
    public String test(){
        return "testsuccess";
    }

    @GetMapping(value = "/demo")
    public String demo(){
        return "请你先登入好吗http://localhost:8070/login?name=test&password=234";
    }
}
