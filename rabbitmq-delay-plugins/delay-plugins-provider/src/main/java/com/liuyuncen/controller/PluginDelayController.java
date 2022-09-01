package com.liuyuncen.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.controller
 * @author: Xiang想
 * @createTime: 2022-09-01  18:53
 * @description: TODO
 * @version: 1.0
 */
@RestController
@RequestMapping("/plugin/delay")
public class PluginDelayController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/pluginDelayMessage")
    public String pluginDelayMessage(Integer millTimes){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay",millTimes);//延迟5秒被删除
        String msg = " 我是 plugins - delay";
        Message message = new Message(msg.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("plugin-delay-exchange","delay",message);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送成功【" + sdf.format(new Date()) + "】");
        return "succ";
    }

}
