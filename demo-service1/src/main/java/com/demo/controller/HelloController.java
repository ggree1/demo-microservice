package com.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@Profile({"dev","insecure"})
@RestController
@RefreshScope //  serviceUrl:port/actuator/refresh  post
public class HelloController {

    @Value("${test.message}")
    private String testMessage;

    @GetMapping("/")
    public String greeting(@Value("${test.message}") String paramTestMessage) {
        return paramTestMessage;
    }

    @GetMapping("/hello")
    public String hello(){
        return testMessage;
    }

    @GetMapping("/hello/{name}")
    Map<String, String> helloName(@PathVariable String name) {
        return Collections.singletonMap("helloName", "hello!, " + name);
    }

}
