package com.liuyuncen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-09-01  18:03
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class PluginDelayRabbitConfig {

    @Bean
    public CustomExchange pluginDelayExchange(){
        Map<String,Object> map = new HashMap<>();
        map.put("x-delayed-type","direct");
        return new CustomExchange("plugin-delay-exchange","x-delayed-message",false,false,map);
    }

    @Bean
    public Queue pluginDelayQueue(){
        return new Queue("plugin-delay-queue");
    }

    @Bean
    public Binding pluginDelayBinding(){
        return BindingBuilder.bind(pluginDelayQueue()).to(pluginDelayExchange()).with("delay").noargs();
    }
}
