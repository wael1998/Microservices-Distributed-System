package com.microcode.customer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/customers")
public record CustomerController (CustomerService CustomerService ){


    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest CustomerRegistrationRequest) {
        log.info("new customer registration {}", CustomerRegistrationRequest);
        CustomerService.registerCustomer(CustomerRegistrationRequest);

    }
}
