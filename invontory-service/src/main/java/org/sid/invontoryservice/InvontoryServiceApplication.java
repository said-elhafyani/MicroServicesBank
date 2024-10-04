package org.sid.invontoryservice;

import org.sid.invontoryservice.enteties.Product;
import org.sid.invontoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InvontoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvontoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration) {
        // expose the id of the entity Product
        repositoryRestConfiguration.exposeIdsFor(Product.class);
        return args -> {
            productRepository.save(new Product(null, "Computer", 8000, 10));
            productRepository.save(new Product(null, "Printer", 2000, 10));
            productRepository.save(new Product(null, "Smartphone", 3000, 10));
            productRepository.findAll().forEach(product ->
                    System.out.println(product.toString()));
        };
    }

}
