package org.sid.billingservice.feign;

import org.sid.billingservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// This interface will allow us to communicate with the customer service using Feign client
@FeignClient(name = "CUSTOMER-SERVICE") // The name of the service to which we want to communicate
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}") // The path of the service to which we want to communicate
    public Customer getCustomerById(@PathVariable Long id);


}
