package org.sid.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

//    // configure the routes to the microservices using the RouteLocatorBuilder static method
//    @Bean
//    RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder){
//        return routeLocatorBuilder.routes()
//                // configure the routes to the microservices using the RouteLocatorBuilder static method
////                .route(r->r.path("/customers/**").uri("http://localhost:8081/"))
////                .route(r->r.path("/products/**").uri("http://localhost:8082/"))
//                // configure the routes to the microservices using the RouteLocatorBuilder dynamic method
//                .route(r->r.path("/customers/**").uri("lb://CUSTOMER-SERVICE/"))
//                .route(r->r.path("/products/**").uri("lb://INVONTORY-SERVICE/"))
//                .build();
//    }

    // configure the routes to the microservices using the RouteLocatorBuilder dynamic method
    //This configuration file is used to configure the Spring Cloud Gateway (dynamic configuration)
    @Bean
    DiscoveryClientRouteDefinitionLocator definitionLocator(ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc, dlp);
    }
}
