/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.decorator;

/**
 * @author sunqinwen
 * @version \: Test.java,v 0.1 2019-03-04 9:00
 */
public class Test {

    public static void main(String[] args) {

        Person person = new Person("sun");

        Txu tx = new Txu();
        Xizhuang xz = new Xizhuang();


        xz.setPerson(person);
        tx.setPerson(xz);

        tx.show();

    }

}
