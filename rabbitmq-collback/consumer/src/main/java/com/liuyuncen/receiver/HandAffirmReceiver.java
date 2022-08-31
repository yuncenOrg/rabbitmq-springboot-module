package com.liuyuncen.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2022-08-31  14:35
 * @description: TODO
 * @version: 1.0
 */
@Component
public class HandAffirmReceiver implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("消息唯一ID值 = " + deliveryTag);
        try{
            byte[] body = message.getBody();
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
            Map<String ,String> map = (Map<String,String>)ois.readObject();
            String code = map.get("code");
            String time = map.get("time");
            String msg = map.get("message");
            ois.close();
            System.out.println("手动确认消息：code："+code+" time："+time+", msg："+msg);
            System.out.println("消费的主题消息来自："+message.getMessageProperties().getConsumerQueue());
            String consumerQueue = message.getMessageProperties().getConsumerQueue();
            if ("fanout.A".equals(consumerQueue)) {
                System.out.println("执行 fanout.A 中的消息的业务处理流程......");
            }
            if ("callBackQueue".equals(consumerQueue)){
                System.out.println("执行 callBackQueue 中的消息的业务处理流程......");
            }
            //  第二个参数，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
            channel.basicAck(deliveryTag,true);
        }catch (Exception e){
            channel.basicReject(deliveryTag,false);
            e.printStackTrace();
        }
    }
}
