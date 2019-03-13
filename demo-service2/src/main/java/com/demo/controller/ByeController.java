package com.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/demo2")
public class ByeController {

    private final AtomicLong listenerCouter = new AtomicLong(0);

    @GetMapping("/")
    String greeting(@Value("${test.message}") String testMessage) {
        return testMessage;
    }

    /**
     * actuator refresh 사용시 @RefreshScope 아닌 이벤트 리스너에도 반응
     */
    @EventListener
    void refresh(RefreshScopeRefreshedEvent e) {
        log.info(":::::::: the refresh count is not at " + this.listenerCouter.incrementAndGet());
    }

    @GetMapping("/bye/{name}")
    Map<String, String> byeName(@PathVariable String name) {
        return Collections.singletonMap("byeName", "Bye!, " + name);
    }

    @GetMapping("/byebye/{name}")
    ResponseEntity<String> getCollection(@PathVariable String name) {
        return ResponseEntity.ok("Bye Bye " + name);
    }
}
