package com.liuyuncen.controller;

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
 * @belongsPackage: com.liuyuncen.controller
 * @author: Xiang想
 * @createTime: 2022-08-29  15:07
 * @description: TODO
 * @version: 1.0
 */
@RestController
public class FanoutController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/fanoutMessage")
    public String fanoutMessage(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",String.valueOf(UUID.randomUUID()));
        map.put("time", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        map.put("message","我是 topic Fanout");
        // 扇形给了路由键也没用
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "ok";
    }

}
