/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.strategy;

/**
 *
 * @author sunqinwen
 * @version \: UninstallContext.java,v 0.1 2019-02-27 12:07 
 *
 */
public class UninstallContext {

    private Game game;

    public UninstallContext() {
        this.game = new Default();
    }

    public UninstallContext(Game game) {
        this.game = game; // 这里根据传入的具体实例赋值
    }

    public void trigger() {
        this.game.uninstall(); // 这里是对行为的封装，只提供uninstall方法的触发
    }

}
