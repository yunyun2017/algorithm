package binaryTree;

import java.util.Stack;

/**
 * 题目：实现二叉树的先序、 中序、 后序遍历， 包括递归方式和非递归方式
 * <p>
 * 先序非递归思路：利用栈
 * 1.根结点非空时，先把根结点压栈；
 * 2.栈不为空时，从栈中弹出一个元素打印，若该元素的右孩子不为空，右孩子压栈，同理对待左孩子；
 * 3.栈不为空，重复第二步，直到栈为空结束
 * <p>
 * 中序非递归思路：
 * 当前节点为空，则从栈中取出一个节点打印，然后去往该节点的右孩子；当前节点不为空，则当前节点入栈，
 * 然后去往当前节点的左孩子
 *
 * 后序非递归思路：
 * 首先我们换个思路，我们先把树按照根右左的方式进行遍历，然后把遍历的结果放到一个栈，再从栈里把所有元素弹出，即是左右根的顺序了；
 * 对于根右左的遍历方式，同先序的根左右的遍历方式，只是在入栈时，先入左再入右，弹出时就是先右后左
 */
public class BinaryTreeTraverse {
    /**
     * 树的节点类型
     */
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 递归先序遍历一棵树
     *
     * @param root 树
     */
    public static void preOrder1(Node root) {
        if (root == null) {
            return;
        }

        System.out.print(root.data + " ");
        preOrder1(root.left);
        preOrder1(root.right);
    }

    /**
     * 非递归先序遍历树
     *
     * @param root 树的根结点
     */
    public static void preOrder2(Node root) {
        if (root == null) {
            return;
        }
        System.out.println("\npreOrder is:");

        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            System.out.print(root.data+" ");

            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
    }

    /**
     * 递归中序遍历一棵树
     *
     * @param root 树
     */
    public static void inOrder1(Node root) {
        if (root == null) {
            return;
        }

        inOrder1(root.left);
        System.out.print(root.data + " ");
        inOrder1(root.right);
    }

    /**
     * 非递归中序遍历
     * @param root 树的根结点
     */
    public static void inOrder2(Node root){
        if(root ==null){
            return;
        }

        System.out.println("\ninorder is:");
        Stack<Node> stack=new Stack<Node>();
        while (!stack.isEmpty() || root!=null){
            if(root!=null){
                stack.push(root);
                root=root.left;
            }else{
                Node temp = stack.pop();
                System.out.print(temp.data+" ");
                root=temp.right;
            }
        }
    }

    /**
     * 递归后序遍历树
     *
     * @param root 树
     */
    public static void posOrder1(Node root) {
        if (root == null) {
            return;
        }

        posOrder1(root.left);
        posOrder1(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * 非递归后序遍历
     * @param root 树
     */
    public static void posOrder2(Node root){
        if(root==null){
            return;
        }

        Stack<Node> stack1=new Stack<Node>();
        Stack<Node> stack2=new Stack<Node>();

        stack1.push(root);
        while (!stack1.isEmpty()){
            root=stack1.pop();
            stack2.push(root);

            if(root.left!=null){
                stack1.push(root.left);
            }
            if(root.right!=null){
                stack1.push(root.right);
            }
        }

        //把stack2中的数都倒出来，并打印
        System.out.println("\nposorder is:");
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop().data+" ");
        }

    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        preOrder1(head);
        preOrder2(head);
        System.out.println();
        System.out.println("=======================");
        inOrder1(head);
        inOrder2(head);
        System.out.println();
        System.out.println("=======================");
        posOrder1(head);
        posOrder2(head);
        System.out.println();
        System.out.println("=======================");
    }
}
