package com.liuyuncen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2022-09-01  14:37
 * @description: TODO
 * @version: 1.0
 */
@SpringBootApplication
public class DelayConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayConsumerApplication.class,args);
    }
}
