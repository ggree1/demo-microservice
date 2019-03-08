package com.demo;

import com.demo.config.ConfigurationProjectProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@Slf4j
@EnableConfigurationProperties
@EnableDiscoveryClient // eureka 외 다른 디스커버리 서버를 사용할 경우
@EnableCircuitBreaker
@EnableHystrixDashboard
//@EnableFeignClients
//@EnableHystrix
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Autowired
    public ServiceApplication(ConfigurationProjectProperties cp) {
        log.info("ConfigurationProjectProperties.projectName : " + cp.getProjectName());
    }

    // RestTemplate 인스턴스는 스프링 클라우드에 의해 인터셉터가 설정된 클라이언트 로드밸런싱 기능 적용
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
