package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: chenlei
 * @date: 2020/11/3
 */
@Component
@FeignClient(value = "cloud-provider-hystrix-payment")
public interface PaymentHystrixService {

    @GetMapping("/payment_ok/hystrix/{id}")
    String paymentInfo_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment_timeout/hystrix/{id}")
    String payment_timeout(@PathVariable("id") Integer id);
}
