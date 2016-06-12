/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试按照顺序,加载事件
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config-register.xml"})
public class HelloIT {

    @Autowired
    private ApplicationContext applicationContext;
    @Test
    public void testPublishEvent() {
        applicationContext.publishEvent(new ContentEvent("今年是龙年的博客更新了"));
    }
}
