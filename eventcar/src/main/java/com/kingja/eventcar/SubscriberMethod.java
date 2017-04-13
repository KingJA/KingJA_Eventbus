package com.kingja.eventcar;

import java.lang.reflect.Method;

/**
 * Description:TODO
 * Create Time:2017/4/13 15:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SubscriberMethod {
    final Method method;
    final Class<?> eventType;

    public SubscriberMethod(Method method, Class<?> eventType) {
        this.method = method;
        this.eventType = eventType;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getEventType() {
        return eventType;
    }
}
