package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableEurekaClient
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(ServiceApplication.class)
                .profiles("prod")
                .run(args);
    }

}
