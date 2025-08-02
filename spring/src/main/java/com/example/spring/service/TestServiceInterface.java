package com.example.spring.service;


import com.example.spring.module.Response;
import com.example.spring.module.User;

public interface TestServiceInterface {
    Response addUser(User user);

    Response delUser(User user);

    Response queryUserList();
}
