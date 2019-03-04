/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.factory;

/**
 * @author sunqinwen
 * @version \: TestMain.java,v 0.1 2019-02-27 9:56
 */
public class TestMain {

    public static void main(String[] args) {
        GameFactory.getGame("hdl").play();
    }

}
