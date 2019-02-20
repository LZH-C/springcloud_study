package com.lzh.springcloudeurekaribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author：龙志华
 * @date： 2019年02月20日18:20
 * @description：
 **/
@Service
public class HellowService {

    @Autowired
    RestTemplate restTemplate;

    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }


}
