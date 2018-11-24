package com.spring.learn.aop.v1;

import org.junit.Test;

/**
 * Created by zfzj on 18/11/22.
 */
public class SimpleAOPTest {

    @Test
    public void getProxy() throws Exception{
        MethodInvocation logTask = () -> System.out.println("log task start");
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();

        // 创建一个 Advice
        Advice beforeAdvice = new BeforeAdvice(helloServiceImpl, logTask);

        // 为目标对象生成代理
        HelloService helloServiceImplProxy = (HelloService)SimpleAOP.getProxy(helloServiceImpl, beforeAdvice);
        helloServiceImplProxy.sayHello();
    }

}
