package com.demo.feign;

import com.demo.domain.Item;
import com.demo.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * demo-service1의 HystrixUserItemClient 처럼 슈퍼타입토큰 사용 없이 Feign을 이용해 복잡한 반환값을 얻을 수 있다
 */

@FeignClient(serviceId = "demo-service2")
interface FeignDemoService2ItemClient {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{name}/items") // demo-service2/users/name/items
    List<Item> getItemListByFeign(@PathVariable("name") String name); // PathVariable에 ("name") 명시해줘야함
}
