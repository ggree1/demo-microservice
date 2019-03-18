package com.demo.feign;

import com.demo.domain.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * demo-service1의 HystrixUserItemClient 에서처럼 슈퍼타입토큰 사용 하지 않고 Feign을 이용해 복잡한 반환값을 얻을 수 있다
 * FeignClient 속성에 url을 명시하지 않으면 순수 feign이 아닌 eureka, hystrix, ribbon을 통해 ServiceId쪽으로 호출한다는 의미임.
 * hystrix와 연동시, feign.hystrix.enabled=true로 하고 fallback 클래스 정의
 */

@FeignClient(serviceId = "demo-service2", fallback = FeignDemoService2ItemServiceFallbackImpl.class)
interface FeignDemoService2ItemService {

    @RequestMapping(method = RequestMethod.GET, value = "/users/{name}/items") // demo-service2/users/name/items
    List<Item> getItemListByFeign(@PathVariable("name") String name); // PathVariable에 ("name") 명시해줘야함
}
