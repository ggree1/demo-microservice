package com.demo.hystrix;

import com.demo.hystrix.model.Item;
import com.demo.hystrix.model.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetItemCommand {

    @Autowired
    RestTemplate restTemplate; // autowired from ServiceApplication

    @HystrixCommand(fallbackMethod = "getFallback")
    public List<User> getUserListWithItem(String name) {

        List<User> userList = new ArrayList<>();
        // demo-service2 의 서비스 요청 - url을 http://localhost/demo-service2/.. 형태 아닌 eureka에 등록된 서비스명만 줄 것.
        // 호출 결과값인 json 을 Item에 맵핑할때 인자 없는 기본생성자 있어야 함.
        List<Item> itemList = (List<Item>)restTemplate.exchange("http://demo-service2/users/"+name+"/items"
                ,HttpMethod.GET,null
                ,new ParameterizedTypeReference<List<Item>>() {}).getBody();
        userList.add(new User(name,name + "@email.com",itemList));

        return userList;
    }

    // demo-service2에 장애가 있어 요청실패할 경우 fallback 메소드 실행
    @SuppressWarnings("unused")
    public List<User> getFallback(String name){

        List<User> usersList = new ArrayList<>();
        usersList.add(new User(name,"default@email.com", null));

        return usersList;
    }
}
