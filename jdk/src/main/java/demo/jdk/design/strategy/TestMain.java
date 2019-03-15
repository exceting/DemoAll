/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.strategy;

/**
 * @author sunqinwen
 * @version \: TestMain.java,v 0.1 2019-02-27 9:56
 */
public class TestMain {

    public static void main(String[] args) {
        new PlayContext("hdl").trigger();
        new UninstallContext(new Hundouluo()).trigger();
        new PlayContext("mxt").trigger();
        new UninstallContext(new Maxituan()).trigger();
    }

}
