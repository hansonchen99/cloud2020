package com.atguigu.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA() {
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "----------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        System.out.println("----------testB:[{}]"+  Thread.currentThread().getName());
        return "----------testB";
    }

    @GetMapping("/testD")
    public String testD() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testD 测试RT");
      //  System.out.println("testD 异常比例");
        int age = 10 / 0;
        return "------testD";
    }


    @GetMapping("/testE")
    public String testE() {
        System.out.println("testE 测试异常数");
        int age = 10 / 0;
        return "------testE 测试异常数";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2", required = false) String p2) {
        return "-------testHotKey";
    }

    public String deal_testHotKey(String p1, String p2,
                                  BlockException exception){
        return "----deal_testHotKey,o(╥﹏╥)o";

    }

}