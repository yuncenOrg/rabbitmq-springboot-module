package com.liuyuncen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-31  16:16
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class RabbitConfig {

    //直连交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("ttl-exchange");
    }

    @Bean
    public Queue ttlQueue(){
        Map<String,Object> map = new HashMap<>();
        // 队列中所有消息5秒后过期
        map.put("x-message-ttl",5000);
        // 队列闲置 10 秒后被删除
        map.put("x-expires",10000);
        // durable：是否持久
        // exclusive: 是否排他，如果为true时，只对当前队列有效，一旦断开，队列立刻删除
        // autoDelete：是否自动删除，前提是必须呀喔有一个消费者先连上，然后所有消费者断开，就自己删除
        return new Queue("ttl-queue",false,false,false,map);
    }

    @Bean
    public Binding ttlBindExchange(@Qualifier("ttlQueue") Queue queue, @Qualifier("directExchange")DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("ttl");
    }
}