package com.demo.hystrix;

import com.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * HystrixUserItemClient 를 사용하기 위한 api gateway용 controller
 */

@Slf4j
@RestController
@RequestMapping("/demo1/users")
public class HystrixUserApiGateway {

    @Autowired
    HystrixUserItemClient hystrixUserService;

    @GetMapping("/{name}")
    public List<User> getUsers(@PathVariable String name) {
        log.info("hystrix Test call param name " + name);
        List<User> userList = hystrixUserService.getUserListWithItem(name);
        return userList;
    }
}
