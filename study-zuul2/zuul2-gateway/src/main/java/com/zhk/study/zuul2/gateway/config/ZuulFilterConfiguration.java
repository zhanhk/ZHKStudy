package com.zhk.study.zuul2.gateway.config;

import com.zhk.study.zuul2.gateway.filters.Healthcheck;
import com.zhk.study.zuul2.gateway.filters.NotFoundEndpoint;
import com.zhk.study.zuul2.gateway.filters.Routes;
import com.zhk.study.zuul2.gateway.filters.ZuulResponseFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulFilterConfiguration {

    @Bean
    public Healthcheck healthcheck() {
        return new Healthcheck();
    }

    @Bean
    public Routes routes() {
        return new Routes();
    }

    @Bean
    public ZuulResponseFilter zuulResponseFilter() {
        return new ZuulResponseFilter();
    }

    @Bean
    public NotFoundEndpoint notFoundEndpoint() {
        return  new NotFoundEndpoint();
    }
}
