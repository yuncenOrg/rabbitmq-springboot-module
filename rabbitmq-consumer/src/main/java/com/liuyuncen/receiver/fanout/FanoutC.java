package com.liuyuncen.receiver.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receiver.fanout
 * @author: Xiang想
 * @createTime: 2022-08-29  15:11
 * @description: TODO
 * @version: 1.0
 */
@Component
@RabbitListener(queues = "fanout.C")
public class FanoutC {
    @RabbitHandler
    public void process(Map message){
        System.out.println("Fanout C 接收到消息 " + message.toString());
    }
}
