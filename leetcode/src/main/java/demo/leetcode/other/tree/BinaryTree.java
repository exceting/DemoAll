/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.leetcode.other.tree;

/**
 * @author sunqinwen
 * @version \: BinaryTree.java,v 0.1 2019-03-22 11:16
 * 简单的二叉查找树实现（假设无重复数据）
 */
public class BinaryTree {

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.insert(6);
        bt.insert(8);
        bt.insert(9);
        bt.insert(1);
        bt.insert(5);
        System.out.println(bt.find(5));
    }

    private TreeNode root;

    public boolean insert(int data) {
        return insert(root, data);
    }

    private boolean insert(TreeNode nowNode, int data) {
        if (root == null) {
            root = new TreeNode(data);
            return true;
        }
        if (data > nowNode.getData()) {
            if (nowNode.getRight() == null) {
                nowNode.setRight(new TreeNode(data));
                return true;
            }
            insert(nowNode.getRight(), data);
        }
        if (data < nowNode.getData()) {
            if (nowNode.getLeft() == null) {
                nowNode.setLeft(new TreeNode(data));
                return true;
            }
            insert(nowNode.getLeft(), data);
        }
        return false;
    }

    public boolean find(int data) {
        return find(root, data);
    }

    private boolean find(TreeNode nowNode, int data) {
        if (nowNode == null) {
            return false;
        }
        if (data > nowNode.getData()) {
            return find(nowNode.getRight(), data);
        }
        if (data < nowNode.getData()) {
            return find(nowNode.getLeft(), data);
        }
        return true;
    }

    public boolean del(int data) {


        return true;
    }

    static class TreeNode {

        private int data;

        private TreeNode left;

        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }

        public int getData() {
            return data;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

}
