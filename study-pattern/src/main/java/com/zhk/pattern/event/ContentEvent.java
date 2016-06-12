/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.zhk.pattern.event;

import org.springframework.context.ApplicationEvent;

/**
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
public class ContentEvent extends ApplicationEvent {
    public ContentEvent(final String content) {
        super(content);
    }
}
