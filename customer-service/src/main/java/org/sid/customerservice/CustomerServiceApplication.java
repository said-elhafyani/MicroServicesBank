package org.sid.customerservice;

import org.sid.customerservice.enteties.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        // expose the id of the entity
        repositoryRestConfiguration.exposeIdsFor(Customer.class);
        return args -> {
            customerRepository.save(new Customer(null, "Hassan", "hassan@gmail.com"));
            customerRepository.save(new Customer(null, "Khalid", "hassan@gmail.com"));
            customerRepository.save(new Customer(null, "kamal", "hassan@gmail.com"));
            customerRepository.save(new Customer(null, "Mohamed", "hassan@gmail.com"));
            customerRepository.findAll().forEach(customer ->
                    System.out.println(customer.toString()));
        };
    }

}
