package com.it.swagger.controller;

import com.it.swagger.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    /**
     * 只要接口中返回值存在实体类，就会被扫描到Swagger中
     * @return
     */
    @PostMapping(value = "/user")
    public User user(){
        return new User();
    }

    /**
     * Get请求中参数不能加 @ApiParam注解，为什么
     * @param username
     * @return
     */
    @ApiOperation("Hello控制类")
    @GetMapping("/hello2")
    public String hello2(String username){
        return "hello" + username;
    }

    @ApiOperation("Post测试类")
    @PostMapping("/postt")
    public User postt(@ApiParam("用户名") User user){
        int i = 5/0;
        return user;
    }
}
