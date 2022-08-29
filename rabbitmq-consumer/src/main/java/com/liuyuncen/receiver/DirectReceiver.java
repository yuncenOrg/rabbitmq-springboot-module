package com.liuyuncen.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen.receiver
 * @author: Xiang想
 * @createTime: 2022-08-29  14:16
 * @description: TODO
 * @version: 1.0
 */
@Component
@RabbitListener(queues = "DirectQueue") // 监听队列名称
public class DirectReceiver {

    /**
     * @description:
     * @author: Xiang想
     * @date: 2022/8/29 2:17 PM
     * @param: [message] 如果推送是 Map 类型，同一个队列接收也一定要是 Map 类型
     * @return: void
     **/
    @RabbitHandler
    public void process(Map message){
        System.out.println("directReceiver 接收到了消息 :"+message.toString());
    }
}
