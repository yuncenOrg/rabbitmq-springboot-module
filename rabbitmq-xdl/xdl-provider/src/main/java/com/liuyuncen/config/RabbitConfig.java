package com.liuyuncen.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-31  17:41
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class RabbitConfig {

    // 定义正常交换机和正常队列信息(交换机名、队列名、路由key)
    public static final String queue_name = "xj_natural_queue";
    public static final String exchange_name = "xj_natural_exchange";
    public static final String routing_key = "xj_natural_routingKey";

    // 定义死信交换机名、死信队列名、路由key
    public static final String queue_name_dead = "xj_dead_queue";
    public static final String exchange_name_dead = "xj_dead_exchange";
    public static final String routing_key_dead = "xj_dead_routingKey";

    /**
     * 设置正常的消息队列；
     * 正常的消息队列具备以下几种功能：
     * 1、消息正常消费，需要绑定对应的消费者(这里为了测试死信，不创建消费者)
     * 2、当消息失效后，需要将指定的消息发送至 死信交换机 中
     * @return
     */
    @Bean(value = "getNaturalQueue")
    public Queue getNaturalQueue(){
        return QueueBuilder.durable(queue_name)
                // 正常的队列，在消息失效后，需要将消息丢入 死信 交换机中
                // 这里只需要针对名称进行绑定
                .withArgument("x-dead-letter-exchange",exchange_name_dead)
                // 丢入 死信交换机，需要设定指定的 routingkey
                .withArgument("x-dead-letter-routing-key",routing_key_dead)
                // 设置正常队列中消息的存活时间为 10s，当然也可以针对单个消息进行设定不同的过期时间
                .withArgument("x-message-ttl",10000)
                // 设定当前队列中，允许存放的最大消息数目
                .withArgument("x-max-length",10)
                .build();
    }

    /**
     * 设定正常的消息交换机
     * @return
     */
    @Bean(value = "getNaturalExchange")
    public Exchange getNaturalExchange(){
        // 这里为了测试，采取 direct exchange
        return ExchangeBuilder.directExchange(exchange_name)
                .durable(true) // 设定持久化
                .build();
    }

    /**
     * 将正常的消息交换机和正常的消息队列进行绑定
     * @param queue
     * @param directExchange
     * @return
     */
    @Bean
    public Binding bindNaturalExchangeAndQueue(
            @Qualifier(value = "getNaturalQueue") Queue queue,
            @Qualifier(value = "getNaturalExchange") Exchange directExchange
    ){

        return BindingBuilder
                // 绑定消息队列
                .bind(queue)
                // 至指定的消息交换机
                .to(directExchange)
                // 匹配 routingkey
                .with(routing_key)
                // 无参数，不加会报错提示
                .noargs();
    }

    /**
     * 定义死信队列
     * @return
     */
    @Bean(value = "getDealQueue")
    public Queue getDealQueue(){
        return QueueBuilder.durable(queue_name_dead).build();
    }

    /**
     * 定义死信交换机
     * @return
     */
    @Bean(value = "getDeadExchange")
    public Exchange getDeadExchange(){
        return ExchangeBuilder.directExchange(exchange_name_dead).durable(true).build();
    }

    /**
     * 将死信交换机和死信队列进行绑定
     * @param deadQueue
     * @param directDeadExchange
     * @return
     */
    @Bean
    public Binding bindDeadExchangeAndQueue(
            @Qualifier(value = "getDealQueue") Queue deadQueue,
            @Qualifier(value = "getDeadExchange") Exchange directDeadExchange
    ){
        return BindingBuilder.bind(deadQueue).to(directDeadExchange).with(routing_key_dead).noargs();
    }
}
