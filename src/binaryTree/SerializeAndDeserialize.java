package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：二叉树的序列化和反序列化
 * <p>
 * 二叉树的序列化：将一棵二叉树按照某个顺序序列化输出到一个字符串
 * <p>
 * 二叉树的反序列化：将一个二叉树的序列化建成一棵二叉树
 */
public class SerializeAndDeserialize {
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 先序序列化
     *
     * @param root 树
     * @return 序列化
     */
    public static String serializeByPre(Node root) {
        if (root == null) {
            return "#_";
        }

        String res = root.data + "_";
        res += serializeByPre(root.left);
        res += serializeByPre(root.right);

        return res;
    }

    /**
     * 先序反序列化
     *
     * @param str 先序序列
     * @return 一棵树
     */
    public static Node deserializeByPre(String str) {
        if (str == null) {
            return null;
        }

        String[] datas = str.split("_");
        Queue<String> queue = new LinkedList<String>();

        for (int i = 0; i < datas.length; i++) {
            queue.add(datas[i]);
        }

        return consTreeByPre(queue);
    }

    /**
     * 先序遍历一棵树
     * @param head 树
     */
    public static void preOrder(Node head){
        if(head==null){
            return;
        }

        System.out.print(head.data+" ");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 利用一个队列构建一个二叉树
     *
     * @param queue 存放先序序列的队列
     * @return 二叉树
     */
    public static Node consTreeByPre(Queue<String> queue) {
        String val = queue.poll();
        if (val.equals("#")) {
            return null;
        }

        Node head = new Node(Integer.valueOf(val));
        head.left = consTreeByPre(queue);
        head.right = consTreeByPre(queue);

        return head;
    }

    public static void main(String[] args) {
        Node head;
        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.right = new Node(5);

        String pre = serializeByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = deserializeByPre(pre);
        System.out.println("deserialize tree by pre-order:");
        preOrder(head);
    }
}
