package com.demo;

import com.demo.filter.CustomZuulPostFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.annotation.Bean;

@Slf4j
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableZuulProxy
@SpringBootApplication
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean
    CustomZuulPostFilter customZuulPostTFilter() {
        return new CustomZuulPostFilter();
    }

    @Bean
    CommandLineRunner commandLineRunner(RouteLocator routeLocator) {
        return args -> routeLocator.getRoutes()
                .forEach(
                        route -> log.info(String.format("routes info : %s (%s) %s",
                                route.getId(), route.getLocation(), route.getFullPath()))
                );
    }
}
