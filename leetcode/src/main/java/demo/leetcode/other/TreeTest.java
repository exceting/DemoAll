/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.other;

/**
 *
 * @author sunqinwen
 * @version \: TreeTest.java,v 0.1 2019-03-22 11:52 
 *
 */
public class TreeTest {

    public static void main(String[] args){
        BinaryTree bt = new BinaryTree();
        bt.insert(6);
        bt.insert(8);
        bt.insert(9);
        bt.insert(1);
        bt.insert(5);
        System.out.println(bt.find(5));
    }

}
