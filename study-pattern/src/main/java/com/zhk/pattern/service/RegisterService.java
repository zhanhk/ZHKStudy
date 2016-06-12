/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.service;

import com.zhk.pattern.event.RegisterEvent;
import com.zhk.pattern.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 注册服务,发布注册成功事件
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
@Service
public class RegisterService {

    protected static final Logger logger = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private ApplicationContext applicationContext;

    public void register(String username, String password) {
        logger.info(username + "注册成功！");
        publishRegisterEvent(new User(username, password));
    }

    private void publishRegisterEvent(User user) {
        applicationContext.publishEvent(new RegisterEvent(user));
    }
}
