/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx.reactor.thread1;

import java.io.IOException;

/**
 * @author sunqinwen
 * @version \: Test.java,v 0.1 2019-03-25 17:56
 */
public class Test {

    public static void main(String[] args) throws IOException {
        new Thread(new Reactor(2333)).start();
    }

}
