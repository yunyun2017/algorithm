package practice05;

/**
 * 题目：在二叉树中找到一个节点的后继节点
 * 现在有一种新的二叉树节点类型如下：
 * public class Node { public int value; public Node left;
 * public Node right; public Node parent;
 * public Node(int data) { this.value = data; }}
 * 该结构比普通二叉树节点结构多了一个指向父节点的parent指针。
 * 假设有一棵Node类型的节点组成的二叉树， 树中每个节点的parent指针都正确地指向自己的父节点，头节点的parent指向null。
 * 只给一个在二叉树中的某个节点node，请实现返回node的后继节点的函数。在二叉树的中序遍历的序列中，
 * node的下一个节点叫作node的后继节点。
 * <p>
 * 思路：
 * 思路一：（常规思路）先把树进行中序遍历，然后在该序列上找到某个节点的后继节点
 * 思路二：分两种情况
 * 情况一：该节点的右子树不为空，则该节点的后继节点是右子树中最左的节点
 * 情况二：该节点的右子树为空，判断该节点是哪个节点的左子树的最后一个节点，则这个节点就是所求的节点
 */
public class findSuccessorNode {
    public static class Node {
        private int value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    //    private static int[] inOrderArr;
    private static int count = 0;

    /**
     * 思路一找树中某个节点的后继节点
     *
     * @param head 树
     * @param node 待查找元素
     * @return 后继节点或null
     */
    public static Node findSuccessorNode1(Node head, Node node) {
        Node[] arr = new Node[100];
        inOrder(head, arr);

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].value == node.value && i < arr.length - 1) {
                return arr[i + 1];
            }
        }

        return null;
    }

    /**
     * 中序遍历树head，遍历结果放arr中
     *
     * @param head 树
     */
    public static void inOrder(Node head, Node[] inOrderArr) {
        if (head == null) {
            return;
        }

        inOrder(head.left, inOrderArr);
        inOrderArr[count++] = head;
        inOrder(head.right, inOrderArr);
    }

    /**
     * 思路二找node节点的后继节点
     *
     * @param node 待找的元素
     * @return node的后继节点或null
     */
    public static Node findSuccessorNode2(Node node) {
        if (node == null) {
            return node;
        }

        if (node.right != null) {//右子树不为空
            Node temp = node.right;
            while (temp.left != null) {
                temp = temp.left;
            }
            return temp;
        } else {//右子树为空
            Node par = node.parent;
            while (par != null && par.right == node) {
                node = par;
                par = par.parent;
            }

            return par;
        }
    }


    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
//        Node succ = findSuccessorNode1(head, test);
        Node succ = findSuccessorNode2(test);
        System.out.println(test.value + " next: " + succ.value);

        test = head.left.left.right;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + findSuccessorNode2(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + findSuccessorNode2(test));
    }

}
