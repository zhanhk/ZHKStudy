/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.listener;

import com.zhk.pattern.event.ContentEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
@Component
public class SunliuListener implements SmartApplicationListener {

    @Override
    public boolean supportsEventType(final Class<? extends ApplicationEvent> eventType) {
        return eventType == ContentEvent.class;
    }

    @Override
    public boolean supportsSourceType(final Class<?> sourceType) {
        return sourceType == String.class;
    }

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        System.out.println("孙六在王五之后收到新的内容：" + event.getSource());
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
