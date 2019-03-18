package com.demo.feign;

import com.demo.domain.Item;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class FeignDemoService2ItemServiceFallbackImpl implements FeignDemoService2ItemService {
    @Override
    public List<Item> getItemListByFeign(String name) {
        return Collections.singletonList(new Item("fallbackItem", 0));
    }
}
