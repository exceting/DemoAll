package demo.leetcode.other.tree;

import java.util.Arrays;

public class TreeToArray {

    static int i = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(21,
                new TreeNode(19, new TreeNode(15, null, null), new TreeNode(20, null, null)),
                new TreeNode(24, new TreeNode(22, null, null), new TreeNode(54, null, null)));

        int[] arr = new int[7];
        treeToArr(root, arr);

        System.out.println(Arrays.toString(arr));
    }

    static void treeToArr(TreeNode root, int[] arr) {
        if (root == null) {
            return;
        }
        treeToArr(root.left, arr);
        arr[i] = root.value;
        i++;
        treeToArr(root.right, arr);
    }

    static class TreeNode {
        private TreeNode left;
        private TreeNode right;

        private int value;

        TreeNode(int value, TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }
}
