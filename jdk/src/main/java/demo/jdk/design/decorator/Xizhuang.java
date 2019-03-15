/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.decorator;

/**
 *
 * @author sunqinwen
 * @version \: Xizhuang.java,v 0.1 2019-03-04 8:59 
 *
 */
public class Xizhuang extends Finery {
    @Override
    public void show() {
        System.out.print("西装");
        super.show();
    }
}
