package com.liuyuncen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-09-01  14:26
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class TtlDelayRabbitConfig {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("ttl-delay-exchange");
    }

    @Bean
    public Queue ttlQueue(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-message-ttl",5000);
        // 过期后进入死信队列
        map.put("x-dead-letter-exchange","ttl-delay-dead-letter-exchange");
        return new Queue("ttl-delay-queue",false,false,false,map);
    }

    @Bean
    public Binding bindTtlDirectExchange(){
        return BindingBuilder.bind(ttlQueue()).to(directExchange()).with("ttlRouting");
    }

    @Bean
    public DirectExchange ttlDelayDeadLetterExchange(){
        return new DirectExchange("ttl-delay-dead-letter-exchange");
    }

    @Bean
    public Queue ttlDelayDeadLetterQueue(){
        return new Queue("ttl-delay-dead-letter-queue");
    }

    @Bean
    public Binding dealLetterQueueBindExchange(){
        return BindingBuilder.bind(ttlDelayDeadLetterQueue()).to(ttlDelayDeadLetterExchange()).with("ttlRouting");
    }

}
