/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.factory;

/**
 *
 * @author sunqinwen
 * @version \: Hundouluo.java,v 0.1 2019-02-27 9:52 
 *
 */
public class Hundouluo implements Game {
    @Override
    public void play() {
        System.out.println("游戏：魂斗罗...playing");
    }

    @Override
    public void uninstall() {
        System.out.println("游戏：魂斗罗...卸载");
    }
}
