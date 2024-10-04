package org.sid.eurikadiskovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurikaDiskoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurikaDiskoveryApplication.class, args);
    }

}
