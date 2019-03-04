/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.strategy;

/**
 *
 * @author sunqinwen
 * @version \: Maxituan.java,v 0.1 2019-02-27 9:53 
 *
 */
public class Maxituan implements Game {
    @Override
    public void play() {
        System.out.println("游戏：马戏团...playing");
    }

    @Override
    public void uninstall() {
        System.out.println("游戏：马戏团...卸载");
    }
}
