package com.example.demo.controller;

import com.example.demo.test.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

@RestController
public class TestController {

    @Resource
    private Hello hello;

    @RequestMapping("/test")
    public String test(){
        hello.sayHello("张三");
        System.out.println("=主线程="+Thread.currentThread().getThreadGroup());
        return "====";
    }
}
