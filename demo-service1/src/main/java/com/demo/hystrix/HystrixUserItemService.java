package com.demo.hystrix;

import com.demo.domain.Item;
import com.demo.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 * demo-service2 의 UserItemController를 호출하는 client
 */

@Slf4j
@Service
public class HystrixUserItemService {

    @Autowired
    RestTemplate restTemplate; // autowired from ServiceApplication

    @HystrixCommand(fallbackMethod = "getFallback")
    public List<User> getUserListWithItem(String name) {

        String serviceUrlToCall = "//demo-service2/users/" + name + "/items"; // 유레카 사용시 등록된 서비스명으로 호출

        List<Item> itemList = restTemplate.exchange(serviceUrlToCall ,HttpMethod.GET,null
                                ,new ParameterizedTypeReference<List<Item>>() {}).getBody();

        return Collections.singletonList(new User(name,name + "@email.com",itemList));

    }

    // demo-service2에 장애가 있어 요청실패할 경우 fallback 메소드 실행
    public List<User> getFallback(String name, Throwable e){

        log.info("fallback called by\n" + e.toString());

        return Collections.singletonList(
                new User("fallbacked : " + name,name + "@fallback.com", null));
    }
}
