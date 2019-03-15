/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.factory;

/**
 * @author sunqinwen
 * @version \: GameFactory.java,v 0.1 2019-02-27 9:54
 */
public class GameFactory {

    public static Game getGame(String name) {
        switch (name) { //根据传来的游戏名（这里偷懒用了首字母），来实例化具体的对象
            case "hdl":
                return new Hundouluo();
            case "mxt":
                return new Maxituan();
            default:
                return new Default();
        }
    }

}
