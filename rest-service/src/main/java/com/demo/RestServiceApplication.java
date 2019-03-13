package com.demo;

import com.demo.domain.Customer;
import com.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class RestServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(CustomerRepository customerRepository) throws Exception {
        return(args -> {
            IntStream.rangeClosed(1, 30).forEach(index ->
                    customerRepository.save(new Customer("name" + index, "Wooo")));
        });
    }
}
