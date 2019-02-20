package com.lzh.springcloudbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@EnableDiscoveryClient
@RestController
@RefreshScope
@SpringBootApplication
public class SpringcloudBusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudBusApplication.class, args);
    }

}
