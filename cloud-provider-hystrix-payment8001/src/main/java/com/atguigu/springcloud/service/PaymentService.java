package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @author: chenlei
 * @date: 2020/11/3
 */
@Service
public class PaymentService {

    public String paymentInfo_ok(Integer id){
        return "当前线程" + Thread.currentThread().getName() + "    paymentInfo_ok ,id =" + id;
    }

    // 如果paymentInfo_TimeOut方法出现异常，就会服务降级去执行paymentInfo_TimeOutHandler方法
    //超过3秒就会出现服务降级
   @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler",commandProperties = {
           @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000")
   })
    public String paymentInfo_TimeOut(Integer id){
       int i = 10/0;
       /*int time = 5;

        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return "当前线程" + Thread.currentThread().getName() + "    paymentInfo_TimeOut ,id =" + id + " 耗时(秒): " ;
    }

    public String paymentInfo_TimeOutHandler(Integer id){
        return "当前线程" + Thread.currentThread().getName() + "    系统繁忙，请稍后再试， o(╥﹏╥)o";
    }

    // 服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求数达到后才计算
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //休眠时间窗
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"), //错误率达到多少跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if(id<0){
             throw new RuntimeException("****id 不能为负数");
      }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
             return "id 不能为负数,请稍后再试， o(╥﹏╥)o id: " + id;
    }
}
