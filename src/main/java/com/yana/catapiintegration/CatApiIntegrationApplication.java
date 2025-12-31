package com.yana.catapiintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.yana.catapiintegration.api")
@SpringBootApplication
public class CatApiIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatApiIntegrationApplication.class, args);
    }

}
