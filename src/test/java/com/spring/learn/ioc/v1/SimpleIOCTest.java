package com.spring.learn.ioc.v1;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by zfzj on 18/11/22.
 */
public class SimpleIOCTest {

    @Test
    public void getBean() throws Exception{
        String location = SimpleIOC.class.getClassLoader().getResource("ioc-v1.xml").getFile();
        SimpleIOC simpleIOC = new SimpleIOC(location);
        Wheel wheel = (Wheel) simpleIOC.getBean("wheel");
        System.out.println(JSONObject.toJSONString(wheel));
        Car car = (Car)simpleIOC.getBean("car");
        System.out.println(JSONObject.toJSONString(car));
    }

}
