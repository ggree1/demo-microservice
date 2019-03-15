package com.demo.service;

import com.demo.domain.Customer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class LoadBalancedRestTemplateService {

    private final RestTemplate restTemplate;

    public LoadBalancedRestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return this.restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }

    public Collection<Customer> getCustomers() {
        // eureka에 등록된 서비스명으로 호출
        return this.exchangeAsList("//rest-service/api/v1/customers", new ParameterizedTypeReference<List<Customer>>() {});
    }

    public ResponseEntity<Customer> getCustomerById(Long id) {
        return this.restTemplate.getForEntity("//rest-service/api/v1/customers/" + id, Customer.class);
    }

}
