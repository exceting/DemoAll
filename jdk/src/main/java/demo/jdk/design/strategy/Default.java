/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.strategy;

/**
 *
 * @author sunqinwen
 * @version \: Default.java,v 0.1 2019-02-27 9:57 
 *
 */
public class Default implements Game {
    @Override
    public void play() {
        System.out.println("游戏：俄罗斯方块...playing");
    }

    @Override
    public void uninstall() {
        System.out.println("游戏：俄罗斯方块...卸载");
    }
}
