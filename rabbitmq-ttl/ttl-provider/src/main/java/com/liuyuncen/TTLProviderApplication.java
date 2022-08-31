package com.liuyuncen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @belongsProject: rabbitmq_springboot
 * @belongsPackage: com.liuyuncen
 * @author: Xiang想
 * @createTime: 2022-08-31  16:15
 * @description: TODO
 * @version: 1.0
 */
@EnableScheduling
@SpringBootApplication
public class TTLProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(TTLProviderApplication.class,args);
    }
}
