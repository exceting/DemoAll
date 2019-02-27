/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.strategy;

/**
 *
 * @author sunqinwen
 * @version \: PlayContext.java,v 0.1 2019-02-27 12:06 
 *
 */
public class PlayContext {

    private Game game;

    public PlayContext() {
        this.game = new Default();
    }

    public PlayContext(String name) {
        switch (name) { //根据传来的游戏名（这里偷懒用了首字母），来实例化具体的对象
            case "hdl":
                this.game = new Hundouluo();
                break;
            case "mxt":
                this.game = new Maxituan();
                break;
            default:
                this.game = new Default();
        }
    }

    public void trigger() {
        this.game.play(); // 这里是对行为的封装，只提供play方法的触发
    }

}
