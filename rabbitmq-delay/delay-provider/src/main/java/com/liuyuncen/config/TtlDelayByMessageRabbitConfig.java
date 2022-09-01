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
public class TtlDelayByMessageRabbitConfig {

    @Bean
    public DirectExchange directByMessageExchange(){
        return new DirectExchange("ttl-delay-by-message-exchange");
    }

    @Bean
    public Queue ttlByMessageQueue(){
        Map<String,Object> map = new HashMap<>();
        // 过期后进入死信队列
        map.put("x-dead-letter-exchange","ttl-delay-by-message-dead-letter-exchange");
        return new Queue("ttl-delay-by-message-queue",false,false,false,map);
    }

    @Bean
    public Binding bindTtlByMessageDirectExchange(){
        return BindingBuilder.bind(ttlByMessageQueue()).to(directByMessageExchange()).with("ttlRouting");
    }

    @Bean
    public DirectExchange ttlDelayByMessageDeadLetterExchange(){
        return new DirectExchange("ttl-delay-by-message-dead-letter-exchange");
    }

    @Bean
    public Queue ttlDelayByMessageDeadLetterQueue(){
        return new Queue("ttl-delay-by-message-dead-letter-queue");
    }

    @Bean
    public Binding dealByMessageLetterQueueBindExchange(){
        return BindingBuilder.bind(ttlDelayByMessageDeadLetterQueue()).to(ttlDelayByMessageDeadLetterExchange()).with("ttlRouting");
    }

}
