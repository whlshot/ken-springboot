package com.ken.other.event;

import org.springframework.context.ApplicationEvent;

/**
 * spring的事件（Application Event）为Bean与Bean之间的消息通信提供了支持。
 * 当一个Bean处理完一个任务之后，希望另外一个Bean知道并能做相应的处理，
 * 这时我们就需要让另外一个Bean监听当前Bean的所发送的事件。
 * <p>
 * Spring的事件需要遵循如下流程：
 * 1）自定义事件，继承ApplicationEvent
 * 2）定义事件监听器，实现ApplicationListener
 * 3）使用容器发布事件
 */
// 自定义事件
public class DemoEvent extends ApplicationEvent {

    private String msg;

    public DemoEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
