package com.lzh.springcloudconfigclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableEurekaClient
public class SpringcloudConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudConfigClientApplication.class, args);
    }

    @Value("${foo}")
    String foo;
    @RequestMapping(value = "/hi")
    public String hi(){
        return foo;
    }

}
