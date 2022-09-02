## SpringBoot 整合 RabbitMQ

> 首次发布时间：2022年8月
> 更新时间：2022年9月
#

本文实操 SpringBootWeb 工程操作 RabbitMQ 实现，消息推送、消费，Direct、Topic、Fanout的使用
更多内容请看下面代码结构
# 

```
rabbitmq_springboot_csdn/
├── rabbitmq-collback               // 消息回调和消息确认
│   ├── consumer
│   └── provider
├── rabbitmq-consumer                   // 基础的消费者
├── rabbitmq-delay                  // 基于死信队列的延时队列
│   ├── delay-consumer
│   └── delay-provider
├── rabbitmq-delay-plugins          // 基于插件的延时队列
│   ├── delay-plugins-consumer
│   └── delay-plugins-provider
├── rabbitmq-provider                   // 基础的生产者
├── rabbitmq-ttl                // TTL 消息过期
│   ├── ttl-consumer
│   └── ttl-provider
└── rabbitmq-xdl                // 死信队列
    ├── xdl-consumer
    └── xdl-provider
```


#

关注「Xiang想」公众号
![](doc/pic/weixin.png)