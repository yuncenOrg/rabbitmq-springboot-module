package com.liuyuncen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-29  14:28
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class TopicRabbitConfig {
    // 绑定键
    public final static String TOPIC_A = "topic.A";
    public final static String TOPIC_B = "topic.B";

    @Bean
    public Queue aQueue(){
        return new Queue(TOPIC_A);
    }

    @Bean
    public Queue bQueue(){
        return new Queue(TOPIC_B);
    }

    @Bean
    TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    /**
     * @description: 只有消息携带的路由键为绑定值才为 topic.A 才会分发到这个队列
     * @author: Xiang想
     * @date: 2022/8/29 2:33 PM
     * @param: []
     * @return: org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingTopicAExchangeMessage(){
        return BindingBuilder.bind(aQueue()).to(topicExchange()).with(TOPIC_A);
    }

    /**
     * @description: 只有消息携带的路由键为绑定值才为 topic.# 以topic.开头的都会发到这个队列
     * @author: Xiang想
     * @date: 2022/8/29 2:33 PM
     * @param: []
     * @return: org.springframework.amqp.core.Binding
     **/
    @Bean
    Binding bindingTopicBExchangeMessage(){
        return BindingBuilder.bind(bQueue()).to(topicExchange()).with("topic.#");
    }
}
