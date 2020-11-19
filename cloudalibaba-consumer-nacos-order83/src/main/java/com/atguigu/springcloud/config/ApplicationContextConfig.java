package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author: chenlei
 * @date: 2020/11/19
 */
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced  // 负载均衡 默认是轮询的策略
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
