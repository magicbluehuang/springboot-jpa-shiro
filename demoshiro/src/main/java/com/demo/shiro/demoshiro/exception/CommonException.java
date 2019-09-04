package com.demo.shiro.demoshiro.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ControllerAdvice
@RestController
public class CommonException  {

    @ExceptionHandler(Exception.class)
    public Map<String,String> commonException(RuntimeException e){
        e.printStackTrace();
        ConcurrentHashMap<String, String> currn = new ConcurrentHashMap<>();
        currn.put("code", "500");
        currn.put("message", e.getMessage());
        return currn;
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Map<String,String> UnauthorizedException(RuntimeException e){
        ConcurrentHashMap<String, String> currn = new ConcurrentHashMap<>();
        currn.put("code", "001");
        currn.put("message", "没有访问权限");
        return currn;
    }
}
