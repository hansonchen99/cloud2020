package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.cloud.stream.messaging.Source;
import java.util.UUID;

/**
 * @author: chenlei
 * @date: 2020/11/18
 * @EnableBinding 注解是绑定channel和exchange绑定在一起
 * output：生产者 input: 消费者
 * 这里是与rabbitMQ进行连接的
 */
@EnableBinding(Source.class)  // 定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    // 消息发送管道
    @Autowired
    private MessageChannel output;

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("生产者发送消息 ------> [{}] " +  serial);
        return null;
    }
}
