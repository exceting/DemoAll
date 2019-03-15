/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.dbpool.druid;

/**
 *
 * @author sunqinwen
 * @version \: A.java,v 0.1 2019-02-11 15:38 
 *
 */
public interface A {
    void one();

    default void getOrDefault(Object key) {
        System.out.println(key);
    }
}
