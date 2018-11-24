package com.spring.learn.aop.v1;

import java.lang.reflect.Proxy;

/**
 * Created by zfzj on 18/11/22.
 */
public class SimpleAOP {

    public static Object getProxy(Object bean, Advice advice){
        return Proxy.newProxyInstance(SimpleAOP.class.getClassLoader(), bean.getClass().getInterfaces(), advice);
    }

}
