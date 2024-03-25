package demo.leetcode.easy;

/**
 * Q.104
 * {@see https://leetcode.cn/problems/maximum-depth-of-binary-tree/}
 */
public class MaxDepth {

    private int maxLevel = 0;
    private int stat = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3,
                new TreeNode(9, null, null),
                new TreeNode(20, new TreeNode(15, null, null), new TreeNode(7, null, null)));

        MaxDepth md = new MaxDepth();
        System.out.println("------>  " + md.maxDepth(root));

        md.clearStat();

        TreeNode root2 = new TreeNode(1, new TreeNode(2, new TreeNode(4, null, null), null),
                new TreeNode(3, null, new TreeNode(5, null, null)));
        System.out.println("------>  " + md.maxDepth(root2));
    }

    private void clearStat() {
        this.maxLevel = 0;
        this.stat = 0;
    }

    public int maxDepth(TreeNode root) {
        maxDepth2(root);
        return this.maxLevel; // 统计的是边数，节点数要+1
    }

    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 非叶子节点，统计
        stat++;
        int l = maxDepth2(root.left);
        int r = maxDepth2(root.right);

        if (l == 0 && r == 0) { // 叶子节点，结算
            maxLevel = Math.max(stat, maxLevel);
        }
        stat--;
        return 1;
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
