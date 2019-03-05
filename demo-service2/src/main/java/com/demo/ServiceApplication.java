package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceApplication.class)
                .profiles("prod")
                .run(args);
    }


    @RestController
    static class secondServiceController {

        private final AtomicLong listenerCouter = new AtomicLong(0);

        @GetMapping("/")
        public String greeting(@Value("${test.message}") String testMessage) {
            return testMessage;
        }

        /**
         * actuator refresh 사용시 @RefreshScope 아닌 이벤트 리스너에도 반응
         */
        @EventListener
        public void refresh(RefreshScopeRefreshedEvent e) {
            log.info(":::::::: the refresh count is not at " + this.listenerCouter.incrementAndGet());
        }
    }
}
