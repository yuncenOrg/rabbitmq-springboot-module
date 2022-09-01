package com.liuyuncen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2022-09-01  18:00
 * @description: TODO
 * @version: 1.0
 */
@SpringBootApplication
@EnableScheduling
public class DelayPluginsProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DelayPluginsProviderApplication.class,args);
    }
}
