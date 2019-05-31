/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.clone;

/**
 * @author sunqinwen
 * @version \: Father.java,v 0.1 2019-05-30 17:20
 */
public abstract class Father implements Cloneable {

    public int a = 0;

    public int b = 1;

    @Override
    protected Object clone() {
        Father o = null;
        try {
            o = (Father) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}
