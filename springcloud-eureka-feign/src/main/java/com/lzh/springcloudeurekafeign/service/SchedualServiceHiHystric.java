package com.lzh.springcloudeurekafeign.service;

import org.springframework.stereotype.Component;

/**
 * @author：龙志华
 * @date： 2019年02月20日18:55
 * @description：
 **/
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}
