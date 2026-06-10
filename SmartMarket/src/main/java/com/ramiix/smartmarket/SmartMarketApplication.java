package com.ramiix.smartmarket;

import jdk.jfr.Enabled;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer

public class SmartMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartMarketApplication.class, args);
    }

}
