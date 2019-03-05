/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.design.decorator;

/**
 * @author sunqinwen
 * @version \: Finery.java,v 0.1 2019-03-04 8:57
 */
public class Finery extends Person {

    private Person person;

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void show() {
        if (person != null) {
            person.show();
        }
    }
}
