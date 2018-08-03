package binaryTree02;

/**
 * 题目：已知一棵完全二叉树， 求其节点的个数
 * 要求：时间复杂度低于O(N)， N为这棵树的节点个数
 * <p>
 * 思路：
 * 1. 找这颗二叉树右子树的最左节点，如果最左节点在最底下一层，说明该二叉树的左子树是满二叉树，用公式算出节点数即可；
 * 该二叉树的右子树是一颗完全二叉树，求节点数，和母问题一样，用递归来算
 * 2. 这颗二叉树右子树的最左节点不再最底下一层，该二叉树的右子树是满二叉树，高度比左子树少1，
 * 用公式计算出右子树的节点数即可；左子树是一颗完全二叉树，求节点数，和母问题一样，递归来求；
 */
public class getCBTNodesNumber {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 求一颗完全二叉树的节点个数
     *
     * @param root 完全二叉树
     * @return 节点个数
     */
    public static int getNodesNumber(Node root) {
        if (root == null) {
            return 0;
        }

        int height = getMostLeft(root, 1);
        return getNodesNumber(root, 1, height);
    }

    /**
     * 求当前这棵完全二叉树node的节点数
     *
     * @param node   当前完全二叉树的根节点
     * @param level  当前node所在的层数
     * @param height 题目所求二叉树的高度
     * @return 完全二叉树的节点数
     */
    public static int getNodesNumber(Node node, int level, int height) {
        if (level == height) {
            return 1;
        }

        if (getMostLeft(node.right, level + 1) == height) {//右子树最左节点的高度等于树高，左子树是满二叉树
            return (int) (Math.pow(2, height - level) + getNodesNumber(node.right, level + 1, height));
        } else {  //右子树最左节点的高度不等于树高，右子树是满二叉树，左子树是完全二叉树
            return (int) (Math.pow(2, height - level - 1) + getNodesNumber(node.left, level + 1, height));
        }
    }

    /**
     * 获得该树的高度
     *
     * @param root  二叉树
     * @param level 当前root所在的层数
     * @return 高度
     */
    public static int getMostLeft(Node root, int level) {
        while (root != null) {
            level++;
            root = root.left;
        }

        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);

        System.out.println(getNodesNumber(head));
    }
}
