package com.demo.feign;

import com.demo.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class FeignDemoService2Controller {

    private final FeignDemoService2ItemService feignDemoService2ItemService;

    @Autowired
    public FeignDemoService2Controller(FeignDemoService2ItemService feignUserItemClient) {
        this.feignDemoService2ItemService = feignUserItemClient;
    }

    @GetMapping("/feign/{name}")
    List<Item> feign(@PathVariable String name) {
        return this.feignDemoService2ItemService.getItemListByFeign(name);
    }


}
