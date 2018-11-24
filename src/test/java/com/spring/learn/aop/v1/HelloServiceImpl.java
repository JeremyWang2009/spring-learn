package com.spring.learn.aop.v1;

/**
 * Created by zfzj on 18/11/22.
 */
public class HelloServiceImpl implements HelloService {

    @Override
    public void sayHello() {
        System.out.println("Hello World");
    }

}
