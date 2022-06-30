package demo.leetcode.other.tree;

// 遍历树
public class TreePrinter {

    public static void main(String[] args) {
        TreeNode t = new TreeNode(10);
        t.add(new TreeNode(20));
        t.add(new TreeNode(15));
        t.add(new TreeNode(9));
        t.add(new TreeNode(35));
        System.out.print(t.data + "、");//第0层：根节点
        System.out.print(t.left.data + "、");//第一层左值
        System.out.print(t.right.data + "、");//第一层右值
        System.out.print(t.right.left.data + "、");//第二层左值
        System.out.print(t.right.right.data);//第二层右值
    }

    static class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }

        TreeNode(TreeNode left, TreeNode right) {
            this.left = left;
            this.right = right;
        }

        void add(TreeNode newNode) {
            TreeNode current = this;
            while (true) {
                if (newNode.data > current.data) {
                    if (current.right != null) {
                        current = current.right;
                    } else {
                        current.right = newNode;
                        break;
                    }
                } else {
                    if (current.left != null) {
                        current = current.left;
                    } else {
                        current.left = newNode;
                        break;
                    }
                }
            }
        }
    }
}
