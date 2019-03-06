package com.demo;

import com.demo.config.ConfigurationProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@Slf4j
@EnableConfigurationProperties
@EnableEurekaClient
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Autowired
    public ServiceApplication(ConfigurationProjectProperties cp) {
        log.info("ConfigurationProjectProperties.projectName : " + cp.getProjectName());
    }

}
