package com.demo.controller;

import com.demo.domain.Customer;
import com.demo.service.LoadBalancedRestTemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/demo2")
public class ServiceController {

    private LoadBalancedRestTemplateService loadBalancedRestTemplateService;

    public ServiceController(LoadBalancedRestTemplateService loadBalancedRestTemplateService) {
        this.loadBalancedRestTemplateService = loadBalancedRestTemplateService;
    }

    @GetMapping(path = "/call/customers")
    public Collection<Customer> getCustomers() throws Exception {
        return this.loadBalancedRestTemplateService.getCustomers();
    }

    @GetMapping(path = "/call/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) throws Exception {
        return this.loadBalancedRestTemplateService.getCustomerById(id);
    }

}
