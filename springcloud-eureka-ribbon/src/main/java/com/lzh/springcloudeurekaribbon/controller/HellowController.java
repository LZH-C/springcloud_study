package com.lzh.springcloudeurekaribbon.controller;

import com.lzh.springcloudeurekaribbon.service.HellowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：龙志华
 * @date： 2019年02月20日18:21
 * @description：
 **/
@RestController
public class HellowController {

    @Autowired
    HellowService helloService;

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }


}
