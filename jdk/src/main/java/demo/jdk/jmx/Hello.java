/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 * @author sunqinwen
 * @version \: Hello.java,v 0.1 2019-01-29 11:40
 */
public class Hello implements HelloMBean {

    private String name;

    public Hello() {
    }

    public Hello(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String printHello() {
        return "Hello " + name;
    }

    @Override
    public String printHello(String whoName) {
        return "Hello  " + whoName;
    }
}
