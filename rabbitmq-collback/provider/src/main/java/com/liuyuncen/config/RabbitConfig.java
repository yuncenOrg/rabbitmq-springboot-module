package com.liuyuncen.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.config
 * @author: Xiang想
 * @createTime: 2022-08-31  13:38
 * @description: TODO
 * @version: 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory factory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(factory);
        // 只有开启了 Mandatory 才能出发回调函数,无论消息吐送结果怎样都强制调用回调函数
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                System.out.println("ConfirmCallback 相关数据 = " + correlationData);
                System.out.println("ConfirmCallback 确认情况 = " + b);
                System.out.println("ConfirmCallback 原因 = " + s);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                System.out.println("ReturnCallback 消息 = " + message);
                System.out.println("ReturnCallback 回应码 = " + i);
                System.out.println("ReturnCallback 回应信息 = " + s);
                System.out.println("ReturnCallback 交换机 = " + s1);
                System.out.println("ReturnCallback 路由键 = " + s2);
            }
        });
        return rabbitTemplate;
    }

    @Bean
    DirectExchange callBackDirectExchange(){
        return new DirectExchange("callBackDirectExchange",true,false);
    }

    @Bean
    public Queue callBackQueue(){
        // 队列名叫 DirectQueue
        return new Queue("callBackQueue",true);
    }

    @Bean
    Binding bindingDirect(){
        // 绑定并且指定 路由键
        return BindingBuilder.bind(callBackQueue()).to(callBackDirectExchange()).with("routingKey");
    }
}
