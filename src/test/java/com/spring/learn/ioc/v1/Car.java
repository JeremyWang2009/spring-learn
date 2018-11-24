package com.spring.learn.ioc.v1;

/**
 * Created by zfzj on 18/11/22.
 */
public class Car {

    private String name;

    private String color;

    private Wheel wheel;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Wheel getWheel() {
        return wheel;
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
}
