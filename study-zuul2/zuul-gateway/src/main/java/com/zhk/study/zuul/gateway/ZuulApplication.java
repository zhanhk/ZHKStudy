package com.zhk.study.zuul.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Configuration;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 下午2:11 18/11/21
 * @copyright Navi WeCloud
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableDiscoveryClient
@EnableZuulProxy
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
