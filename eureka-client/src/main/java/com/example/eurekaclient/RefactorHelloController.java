package com.example.eurekaclient;

import com.example.servicehiapi.dto.User;
import com.example.servicehiapi.service.HelloService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RefactorHelloController implements HelloService {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }

    @Override
    public User hello(String name, Integer age) {
        return new User(name, age);
    }

    @Override
    public String hello(User user) {
        return "hello" + user.getName() + ", " + user.getAge();
    }
}
