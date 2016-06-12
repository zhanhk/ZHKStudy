package com.zhk.pattern.event;

import com.zhk.pattern.pojo.User;
import org.springframework.context.ApplicationEvent;

/**
 * 注册监听事件
 * Created with IntelliJ IDEA.
 * User: zhanhk
 * Date: 16/6/12
 * Time: 下午2:42
 */
public class RegisterEvent extends ApplicationEvent {

    public RegisterEvent(User source) {
        super(source);
    }
}
