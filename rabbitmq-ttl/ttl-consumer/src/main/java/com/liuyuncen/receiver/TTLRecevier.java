package com.liuyuncen.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receiver
 * @author: Xiang想
 * @createTime: 2022-08-31  16:25
 * @description: TODO
 * @version: 1.0
 */
@Component
public class TTLRecevier {

    @RabbitHandler
    @RabbitListener(queues = "ttl-queue")
    public void ttlConsumer(String message){
        System.out.println("message = " + message);
    }

}
