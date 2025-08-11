package com.getgoods.ecommerceserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EcommerceServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceServerApplication.class, args);
    }

}
