package com.liuyuncen.config;

import com.liuyuncen.receiver.HandAffirmReceiver;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-31  14:33
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class MessageListenerConfig {

    @Autowired
    private CachingConnectionFactory connectionFactory;

    @Autowired
    private HandAffirmReceiver handAffirmReceiver;

    /**
     * @description: 指定队列消息到 指定的 receiver 监听器中
     * @author: Xiang想
     * @date: 2022/8/31 2:45 PM
     * @param: []
     * @return: org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
     **/
    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setConcurrentConsumers(1);
        container.setMaxConcurrentConsumers(1);
        // MANUAL 为手动确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 第一个队列
        // container.setQueueNames("callBackQueue");
        // 如果同时设置多个队列，前提是这些队里必须是已经创建好的
        container.setQueueNames("callBackQueue","fanout.A");

        container.setMessageListener(handAffirmReceiver);
        return container;
    }
}
