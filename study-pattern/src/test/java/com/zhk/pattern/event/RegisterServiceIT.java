/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.event;

import com.zhk.pattern.service.RegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试事件监听,事件被异步调用@Async
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config-register.xml"})
public class RegisterServiceIT {

    @Autowired
    private RegisterService registerService;

    @Test
    public void testRegister() {
        registerService.register("long", "123");
    }
}
