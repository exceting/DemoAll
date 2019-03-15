/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.decorator;

/**
 *
 * @author sunqinwen
 * @version \: Txu.java,v 0.1 2019-03-04 8:58 
 *
 */
public class Txu extends Finery {
    @Override
    public void show() {
        System.out.print("T恤");
        super.show();
    }
}
