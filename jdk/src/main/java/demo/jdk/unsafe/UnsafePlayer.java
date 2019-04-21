/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author sunqinwen
 * @version \: UnsafePlayer.java,v 0.1 2019-04-17 18:27
 */
public class UnsafePlayer {
    public static void main(String[] args) throws Exception {
        //通过反射实例化Unsafe
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        //实例化Player
        Player player = (Player) unsafe.allocateInstance(Player.class);
        player.setName("li lei");
        System.out.println(player.getName());
    }
}

class Player {
    private String name;

    private Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
