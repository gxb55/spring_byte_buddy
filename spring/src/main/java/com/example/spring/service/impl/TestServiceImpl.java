package com.example.spring.service.impl;


import com.example.spring.module.Response;
import com.example.spring.module.User;
import com.example.spring.service.TestServiceInterface;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TestServiceImpl implements TestServiceInterface {
    private Map<Long, User> map=new HashMap<>();
    @Override
    public Response addUser(User user) {
        long l = System.currentTimeMillis();
        user.setId(l);
        map.put(l, new User(l, user.getName(), user.getAge()));
        return Response.getSuccessResponse();
    }

    @Override
    public Response delUser(User user) {
        Long id = user.getId();
        map.remove(id);
        return Response.getSuccessResponse();
    }

    @Override
    public Response queryUserList() {
        List<User> collect = map.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
        Response successResponse = Response.getSuccessResponse();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("userList", collect);
        successResponse.setData(map1);
        return successResponse;

    }
}
