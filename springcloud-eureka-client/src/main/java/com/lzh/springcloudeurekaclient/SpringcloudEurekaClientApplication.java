package com.lzh.springcloudeurekaclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient /*用于eureka的扫描和发现*/
@EnableDiscoveryClient /*用于其他服务注册中心包含eureka的扫描和发现*/
@SpringBootApplication
@RestController
public class SpringcloudEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaClientApplication.class, args);
    }

    @Value("${server.port}")
    String port;
    @RequestMapping("/hi")
    public String home(@RequestParam String name) {
        return "hi "+name+",i am from port:" +port;
    }


}
