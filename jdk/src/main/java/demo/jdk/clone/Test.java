/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.clone;

/**
 * @author sunqinwen
 * @version \: Test.java,v 0.1 2019-05-30 17:23
 */
public class Test {

    public static void main(String[] args) {
        Father father = new Child();
        System.out.println(father.a+"   "+father.b+"    "+((Child) father).c +"   "+((Child) father).d+"   "+((Child) father).sun);

        Father father1 = (Father) father.clone();
        System.out.println(father1.a+"   "+father1.b+"    "+((Child) father1).c +"   "+((Child) father1).d+"     "+((Child) father1).sun);
    }

}
