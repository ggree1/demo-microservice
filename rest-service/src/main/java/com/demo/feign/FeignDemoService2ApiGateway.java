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
public class FeignDemoService2ApiGateway {

    private final FeignDemoService2ItemClient feignDemoService2ItemClient;

    @Autowired
    public FeignDemoService2ApiGateway(FeignDemoService2ItemClient feignUserItemClient) {
        this.feignDemoService2ItemClient = feignUserItemClient;
    }

    @GetMapping("/feign/{name}")
    List<Item> feign(@PathVariable String name) {
        return this.feignDemoService2ItemClient.getItemListByFeign(name);
    }


}
