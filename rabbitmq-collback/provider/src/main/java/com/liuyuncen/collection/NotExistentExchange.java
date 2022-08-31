package com.liuyuncen.collection;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.collection
 * @author: Xiang想
 * @createTime: 2022-08-31  13:48
 * @description: TODO
 * @version: 1.0
 */
@RestController
public class NotExistentExchange {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/notExistentMessage")
    public String notExistentMessage(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",String.valueOf(UUID.randomUUID()));
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("message","hi 你肯定找不到这个交换机");
        // 指向交换机和路由键
        rabbitTemplate.convertAndSend("not-existent-exchange","routingKey",map);
        return "ok";
    }
}
