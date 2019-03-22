package com.demo;

import com.demo.domain.Customer;
import com.demo.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.stream.IntStream;

@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
@EnableSwagger2
@RefreshScope
@SpringBootApplication
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) throws Exception {
        return (args -> {
            IntStream.rangeClosed(1, 30).forEach(index ->
                    customerRepository.save(new Customer("name" + index, "Wooo")));
        });
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any()) // 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .paths(PathSelectors.ant("/api/**")) // 그중 /api/** 인 URL들만 필터링
                .build();
    }

}
