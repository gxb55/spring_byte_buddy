package com.example.spring.controller;

import com.example.spring.module.Response;
import com.example.spring.module.User;
import com.example.spring.service.TestServiceInterface;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Resource
    private TestServiceInterface serviceInterface;

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping("/hello")
    public String sayHello() {
        return "hi Boot";
    }
    @PostMapping(value = "/addUser")
    public Response addUser(@RequestBody User user) {
        return serviceInterface.addUser(user);
    }
    @PostMapping(value = "/queryUserList")
    public Response queryUserList() {
        return serviceInterface.queryUserList();
    }
}
