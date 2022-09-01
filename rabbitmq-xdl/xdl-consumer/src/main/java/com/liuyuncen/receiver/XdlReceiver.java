package com.liuyuncen.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receiver
 * @author: Xiang想
 * @createTime: 2022-09-01  11:04
 * @description: TODO
 * @version: 1.0
 */
@Component
@RabbitListener(queues = "xj_dead_queue")
public class XdlReceiver {

    @RabbitHandler
    public void process(String msg){
        System.out.println(msg);
    }
}



