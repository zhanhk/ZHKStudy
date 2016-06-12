/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.listener;

import com.zhk.pattern.event.ContentEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
@Component
public class LisiListener implements ApplicationListener<ApplicationEvent> {

    @Override
    public void onApplicationEvent(final ApplicationEvent event) {
        if(event instanceof ContentEvent) {
            System.out.println("李四收到了新的内容：" + event.getSource());
        }
    }
}
