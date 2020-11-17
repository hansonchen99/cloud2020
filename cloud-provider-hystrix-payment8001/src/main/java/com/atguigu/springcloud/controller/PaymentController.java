package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: chenlei
 * @date: 2020/11/3
 */
@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment_ok/hystrix/{id}")
    public String paymentInfo_ok(@PathVariable("id") Integer id){
        String str = paymentService.paymentInfo_ok(id);
        log.info("str = " + str);
        return str;
    }

    @GetMapping("/payment_timeout/hystrix/{id}")
    public String payment_timeout(@PathVariable("id") Integer id){
        String str = paymentService.paymentInfo_TimeOut(id);
        log.info("str = " + str);
        return str;
    }

    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String res = paymentService.paymentCircuitBreaker(id);
        log.info("*****result: " + res);
        return res;
    }

}
