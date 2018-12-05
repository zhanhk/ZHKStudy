package com.zhk.study.zuul2.gateway.config;

import com.netflix.zuul.FilterFactory;
import com.netflix.zuul.filters.ZuulFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;

/**
 * @Desc:
 * @Author: zhanhk
 * @Date: Created in 上午10:12 18/11/21
 * @copyright Navi WeCloud
 */

@AllArgsConstructor
public class SpringFilterFactory implements FilterFactory {

    private final ApplicationContext applicationContext;

    @Override
    public ZuulFilter newInstance(Class clazz) throws Exception {
        return (ZuulFilter) applicationContext.getBean(clazz);
    }
}
