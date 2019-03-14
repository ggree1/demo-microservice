package com.demo.hystrix;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class ItemController {

    @Autowired
    HttpServletRequest request;

    @GetMapping(path = "/{name}/items")
    public List<Item> getItem(@PathVariable String name) {
        log.info("getItem : " + name);
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item("item1", 5));
        itemList.add(new Item("item2", 10));

        return itemList;
    }
}
