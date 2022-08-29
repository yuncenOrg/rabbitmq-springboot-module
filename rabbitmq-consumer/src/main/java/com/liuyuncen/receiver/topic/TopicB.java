package com.liuyuncen.receiver.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receiver.topic
 * @author: Xiang想
 * @createTime: 2022-08-29  14:47
 * @description: TODO
 * @version: 1.0
 */
@Component
@RabbitListener(queues = "topic.B")
public class TopicB {

    @RabbitHandler
    public void process(Map message){
        System.out.println("Topic B 接收消息："+message.toString());
    }
}
