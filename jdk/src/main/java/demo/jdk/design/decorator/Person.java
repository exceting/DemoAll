/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.decorator;

/**
 * @author sunqinwen
 * @version \: Person.java,v 0.1 2019-03-04 8:55
 */
public class Person {

    private String name;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void show() {
        System.out.print(" 扮相的" + name);
    }

}
