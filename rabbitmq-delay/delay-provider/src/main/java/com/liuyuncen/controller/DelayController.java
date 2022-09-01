package com.liuyuncen.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.controller
 * @author: Xiang想
 * @createTime: 2022-09-01  14:34
 * @description: TODO
 * @version: 1.0
 */
@RestController
@RequestMapping("/delay")
public class DelayController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/delayMessage")
    public String delayMessage(){
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String msg = time + " 我是ttl-delay 消息";
        System.out.println("provider 发送消息：" + msg);
        rabbitTemplate.convertAndSend("ttl-delay-exchange","ttlRouting",msg);
        return "ok";
    }

    @GetMapping("/delayByMessage")
    public String delayByMessage(String millTimes){

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String msg = time + " 我是ttl-delay 消息 过期时长为:"+millTimes;
        MessageProperties messageProperties = new MessageProperties();
        // 设置单条过期时间,单位毫秒
        messageProperties.setExpiration(millTimes);
        Message message = new Message(msg.getBytes(), messageProperties);
        System.out.println("provider 发送消息：" + msg);
        rabbitTemplate.convertAndSend("ttl-delay-by-message-exchange","ttlRouting",message);
        return "ok";
    }
}
