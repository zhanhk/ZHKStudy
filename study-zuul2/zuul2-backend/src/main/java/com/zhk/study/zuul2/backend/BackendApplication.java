package com.zhk.study.zuul2.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 上午11:32 18/11/22
 * @copyright Navi WeCloud
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
