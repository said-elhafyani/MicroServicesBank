package org.sid.billingservice.feign;

import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// open feign client to communicate with the inventory service
@FeignClient(name = "INVENTORY-SERVICE")
public interface ProductRestClient {
    // get all products
    @GetMapping(path = "/products")
    PagedModel<Product> pageProducts();
    // return PageModel of Product
    //PagedModel<Product> pageProducts(@RequestParam(value = "page") int page,@RequestParam(value = "size") int size);

    @GetMapping(path = "/products/{id}")
    Product getProductById(@PathVariable(value = "id") Long id);

}
