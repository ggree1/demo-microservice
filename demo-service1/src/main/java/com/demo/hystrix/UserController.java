package com.demo.hystrix;

import com.demo.hystrix.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    GetItemCommand getItemCommand;

    @GetMapping("/{name}")
    public List<User> getUsers(@PathVariable String name) {
        log.info("User service : " + name);
        List<User> userList = getItemCommand.getUserListWithItem(name);
        return userList;
    }
}
