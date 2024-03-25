package demo.leetcode.easy;

/**
 * Q.543
 * 给你一棵二叉树的根节点，返回该树的 直径 。
 * 二叉树的 直径 是指树中任意两个节点之间最长路径的 长度 。这条路径可能经过也可能不经过根节点 root 。
 * 两节点之间路径的 长度 由它们之间**边数**表示。
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：3
 * 解释：3 ，取路径 [4,2,1,3] 或 [5,2,1,3] 的长度。
 * 输入：root = [1,2]
 * 输出：1
 */
public class DiameterOfBinaryTree {

    private int max = 0;

    public static void main(String[] args) {
        DiameterOfBinaryTree tree = new DiameterOfBinaryTree();
        tree.diameterOfBinaryTree(new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5, null, null)),
                new TreeNode(3, null, null)));

        System.out.println("max = " + tree.max);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int l = diameterOfBinaryTree(root.left);
        int r = diameterOfBinaryTree(root.right);

        max = Math.max((l + r), max); // 计算直径时会连接父节点，多出两条边，因此下方左右各+1就抵消了多出来的两条边

        return Math.max(l, r) + 1; // 实际返回的是子树最大的那条路径上的节点数（即：边数+1）
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
