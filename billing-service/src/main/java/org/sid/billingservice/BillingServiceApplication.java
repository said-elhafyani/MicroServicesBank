package org.sid.billingservice;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.CustomerRestClient;
import org.sid.billingservice.feign.ProductRestClient;
import org.sid.billingservice.model.Customer;
import org.sid.billingservice.model.Product;
import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
// enable feign client to communicate with other services
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository ,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient) {
        return args -> {
            Customer customer = customerRestClient.getCustomerById(1L);
            Bill bill = billRepository.save(new Bill(null,new Date(),null, customer.getId(), null));
            System.out.println("*******************11");
            PagedModel<Product> productPagedModel = productRestClient.pageProducts();
            System.out.println("*******************22");
            productPagedModel.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+ (int)(Math.random()*100));
                productItem.setBill(bill);
                productItem.setProductId(product.getId());
                productItemRepository.save(productItem);
            });

        };

    }

}
