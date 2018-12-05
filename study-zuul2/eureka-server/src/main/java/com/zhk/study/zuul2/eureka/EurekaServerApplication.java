package com.zhk.study.zuul2.eureka;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 上午8:53 18/11/28
 * @copyright Navi WeCloud
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
