package com.liuyuncen.receive;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.recive
 * @author: Xiang想
 * @createTime: 2022-09-01  14:38
 * @description: TODO
 * @version: 1.0
 */
@Component
public class DelayReceive {

//    /**
//     * @description: 消费正常的信息
//     * @author: Xiang想
//     * @date: 2022/9/1 2:48 PM
//     * @param: [message]
//     * @return: void
//     **/
//    @RabbitHandler
//    @RabbitListener(queues = "ttl-delay-queue")
//    public void getProcess(String message){
//        System.out.println(message);
//    }



    @RabbitHandler
    @RabbitListener(queues = "ttl-delay-dead-letter-queue")
    public void getTtlDelayProcess(String message){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("【延迟队列】【死信消息】 接到到的时间是"+ time+" -- "+message);
    }

    @RabbitHandler
    @RabbitListener(queues = "ttl-delay-by-message-dead-letter-queue")
    public void getTtlByMessageDelayProcess(String message){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println("【延迟队列】【死信消息】 接到到的时间是"+ time+" -- "+message);
    }
}
