package practice05;

import java.util.LinkedList;
import java.util.Queue;
import java.util.SortedMap;
import java.util.Stack;

/**
 * 题目：判断一棵树是否是搜索二叉树、 判断一棵树是否是完全二叉树
 * <p>
 * 思路：
 * 判断搜索二叉树：（搜索二叉树：对于一棵树中的任何一颗子树，左子树上的节点值都比根节点小，右子树上的节点值都比根节点大）
 * 中序遍历二叉树得到的序列如果是升序的，则该二叉树就是搜索二叉树
 * <p>
 * 判断完全二叉树：（基于树的层次遍历）
 * 1. 一个节点有右孩子，没有左孩子，则该树不是完全二叉树，否则进入下一步；
 * 2.一个节点的孩子不全（没有孩子；有左无右），该节点之后的所有节点都是叶子节点，则该树是完全二叉树，否则不是；
 * 3. 一个节点的左右孩子是全的，直接跳到下一个节点
 */
public class IsBSTAndCBT {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 判断一棵树是否是搜索二叉树（递归遍历二叉树）
     *
     * @param root 树
     * @return true/false
     */
    public static boolean isBST1(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<Node>();
        int pre = Integer.MIN_VALUE;
        inOrder(root, queue);
        while (!queue.isEmpty()) {
            int cur = queue.poll().data;
            if (cur < pre) {
                return false;
            }
            pre = cur;
        }
        return true;

    }

    /**
     * 中序遍历二叉树，中序序列放在队列中
     *
     * @param root  树
     * @param queue 存放中序序列的队列
     * @return 存放中序序列的队列
     */
    public static Queue<Node> inOrder(Node root, Queue<Node> queue) {
        if (root == null) {
            return queue;
        }

        inOrder(root.left, queue);
        queue.add(root);
        inOrder(root.right, queue);

        return queue;
    }

    /**
     * 判断一颗二叉树是否是搜索二叉树（非递归方式中序遍历二叉树）
     *
     * @param root 树
     * @return true/false
     */
    public static boolean isBST2(Node root) {
        if (root == null) {
            return true;
        }

        int pre = Integer.MIN_VALUE;
        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                System.out.print(root.data + " ");
                //打印这里换成当前节点值和前面一个节点值比较
                if (pre > root.data) {
                    return false;
                }
                pre = root.data;
                root = root.right;
            }

        }

        return true;
    }

    /**
     * 判断一棵树是否是完全二叉树
     *
     * @param root 树
     * @return true/false
     */
    public static boolean isCBT(Node root) {
        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<Node>();
        boolean isLeaf = false;
        queue.add(root);
        while (!queue.isEmpty() && !isLeaf) {
            root = queue.poll();
            System.out.print(root.data + " ");

            if (root.left == null && root.right != null) {
                return false;
            }

//            if (root.right == null) {//该节点后面的所有节点都是叶节点
//                isLeaf = true;
//            }

            if (root.left != null) {
                queue.add(root.left);
            }
            if (root.right != null) {
                queue.add(root.right);
            }else{      //该节点后面的所有节点都是叶节点
                isLeaf = true;
            }
        }

        //检查剩下的节点是否都是叶节点
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null || root.right != null) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
//        head.left.right = new Node(3);
        head.right.left = new Node(5);

//        System.out.println(isBST1(head));
//        System.out.println(isBST2(head));
        System.out.print(isCBT(head));
    }
}
