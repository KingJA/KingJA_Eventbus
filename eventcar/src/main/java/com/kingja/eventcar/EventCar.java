package com.kingja.eventcar;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import static android.content.ContentValues.TAG;

/**
 * Description:TODO
 * Create Time:2017/4/13 15:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EventCar {
    private static volatile EventCar mInstance;
    private MethodFinder mMethodFinder;
    private final Map<Class<?>, CopyOnWriteArrayList<Subscription>> subscriptionsByEventType;

    private EventCar() {
        this(new EventCarBuilder());
    }

    private EventCar(EventCarBuilder builder) {
        mMethodFinder = new MethodFinder();
        subscriptionsByEventType = new HashMap<>();
    }

    public static EventCar getDefault() {
        if (mInstance == null) {
            synchronized (EventCar.class) {
                if (mInstance == null) {
                    mInstance = new EventCar();
                }
            }
        }
        return mInstance;
    }

    public void register(Object subsciber) {
        Class<?> subsciberClass = subsciber.getClass();
        List<SubscriberMethod> subscriberMethods = mMethodFinder.findMehod(subsciberClass);
        for (SubscriberMethod subscriberMethod : subscriberMethods) {
            subsciber(subsciber, subscriberMethod);
        }
    }

    private void subsciber(Object subsciber, SubscriberMethod subscriberMethod) {
        Class<?> subsciberClass = subsciber.getClass();
        Class<?> eventType = subscriberMethod.getEventType();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(subsciberClass);
        if (subscriptions == null) {
            subscriptions = new CopyOnWriteArrayList();
        }
        subscriptions.add(new Subscription(subsciber, subscriberMethod));
        subscriptionsByEventType.put(eventType, subscriptions);
    }

    public void post(Object event) {
        MessageEvent msg = (MessageEvent) event;
        Class<?> eventType = event.getClass();
        CopyOnWriteArrayList<Subscription> subscriptions = subscriptionsByEventType.get(eventType);
        if (subscriptions != null && !subscriptions.isEmpty()) {
            for (Subscription subscription : subscriptions) {
                try {
                    subscription.subscriberMethod.method.invoke(subscription.subscriber, event);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void unRegister(Object object) {

    }

    static class EventCarBuilder {

    }
}
