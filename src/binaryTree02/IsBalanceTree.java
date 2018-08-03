package binaryTree02;

import practice02.MaxGap;

/**
 * 题目：判断一棵二叉树是否是平衡二叉树
 * 平衡二叉树：一颗树中任何一棵子树的左右子树的高度差绝对值不大于1
 * 思路：（根据层次遍历来遍历二叉树）
 * 1. 判断根节点左子树是否是平衡二叉树，若不是，则返回false，否则，进入下一步；
 * 2. 判断根节点右子树是否是平衡二叉树，若不是，则返回false，否则，进入下一步；
 * 3. 根节点的左子树和右子树高度差的绝对值是否小于等于1，若是，则该树是平衡二叉树，若不是，则该树不是平衡二叉树
 */
public class IsBalanceTree {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 定义返回值类型
     */
    public static class returnData {
        private boolean isBalance;  //是否是平衡二叉树
        private int height;     //树的高度

        public returnData(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

    /**
     * 判断一棵树是否是平衡二叉树
     *
     * @param root 树
     * @return true/false
     */
    public static boolean isBalance(Node root) {
        returnData balan = judgeBalance(root);

        return balan.isBalance;
    }

    /**
     * 判断子树是否是平衡二叉树
     *
     * @param root 子树
     * @return returnData
     */
    public static returnData judgeBalance(Node root) {
        if (root == null) {
            return new returnData(true, 0);
        }

        returnData leftData = judgeBalance(root.left);
        if (!leftData.isBalance) {
            return new returnData(false, 0);
        }
        returnData rightData = judgeBalance(root.right);
        if (!rightData.isBalance) {
            return new returnData(false, 0);
        }

        if (Math.abs(leftData.height - rightData.height) > 1) {
            return new returnData(false, 0);
        }

        return new returnData(true, Math.max(leftData.height, rightData.height) + 1);

    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        System.out.println(isBalance(head));
    }

}
