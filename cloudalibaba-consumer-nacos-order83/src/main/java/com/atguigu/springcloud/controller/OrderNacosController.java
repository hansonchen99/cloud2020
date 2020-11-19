package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author: chenlei
 * @date: 2020/11/19
 */
@RestController
public class OrderNacosController {

    @Value("${service-url.nacos-user-service}")
    private String nacosServiceURL;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id") Long id){
       return restTemplate.getForObject(nacosServiceURL + "/payment/nacos/" + id, String.class);
    }
}
