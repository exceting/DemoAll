/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 * @author sunqinwen
 * @version \: HelloMBean.java,v 0.1 2019-01-29 11:39
 */
public interface HelloMBean {
    String getName();

    void setName(String name);

    String printHello();

    String printHello(String whoName);
}
