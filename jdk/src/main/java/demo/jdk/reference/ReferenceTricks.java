/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */

package demo.jdk.reference;

/**
 * @author sunqinwen
 * @version \: ReferenceTricks.java,v 0.1 2019-05-13 18:48
 */
public class ReferenceTricks {

    public static void main(String[] args) {
        ReferenceTricks r = new ReferenceTricks();
        // reset integer
        r.i = 0;
        System.out.println("Before changeInteger:" + r.i);
        changeInteger(r);
        System.out.println("After changeInteger:" + r.i);

        // just for format
        System.out.println();

        // reset integer
        r.i = 0;
        System.out.println("Before changeReference:" + r.i);
        changeReference(r);
        System.out.println("After changeReference:" + r.i);
    }

    private static void changeReference(ReferenceTricks r) {
        r = new ReferenceTricks();
        r.i = 5;
        System.out.println("In changeReference: " + r.i);
    }

    private static void changeInteger(ReferenceTricks r) {
        r.i = 5;
        System.out.println("In changeInteger:" + r.i);
    }

    public int i;

}
