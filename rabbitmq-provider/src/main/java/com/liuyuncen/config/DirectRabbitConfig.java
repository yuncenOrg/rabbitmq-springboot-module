package com.liuyuncen.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-29  13:49
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class DirectRabbitConfig {

    /**
     *  Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
     *  durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     *  exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     *  autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除
     * @description:
     * @author: Xiang想
     * @date: 2022/8/29 1:52 PM
     * @param: []
     * @return: org.springframework.amqp.core.Queue
     **/
    @Bean
    public Queue TestDirectQueue(){
        // 队列名叫 DirectQueue
        return new Queue("DirectQueue",true);
    }

    @Bean
    DirectExchange TestDirectExchange(){
        // 交换机名叫 directExchange
        // 如果没有叫这个的交换机 rabbitmq 会自己创建
        return new DirectExchange("directExchange",true,false);
    }

    @Bean
    Binding bindingDirect(){
        // 绑定并且指定 路由键
        return BindingBuilder.bind(TestDirectQueue()).to(TestDirectExchange()).with("routingKey");
    }

}
