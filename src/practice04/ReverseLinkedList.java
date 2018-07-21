package practice04;

/**
 * 题目：分别实现反转单向链表和反转双向链表的函数
 * 要求：如果链表长度为N， 时间复杂度要求为O(N)， 额外空间复杂度要求为O(1)
 */
public class ReverseLinkedList {
    /**
     * 定义单向链表节点结构
     */
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 反转单向链表
     *
     * @param head 传入一个单向链表
     * @return 返回反转后的单向链表
     */
    public static Node reverseList(Node head) {
        Node pre = null;
        Node next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 定义双向链表节点结构
     */
    public static class DoubleNode {
        int data;
        DoubleNode pre;
        DoubleNode next;

        public DoubleNode(int data) {
            this.data = data;
        }
    }

    /**
     * 反转一个双向链表
     *
     * @param head 传入的双向链表
     * @return 反转之后的双向链表
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    /**
     * 打印单向链表
     * @param head 单向链表
     */
    public static void printList(Node head){
        System.out.println("the linked List is: ");
        while (head!=null){
            System.out.print(head.data+" ");
            head=head.next;
        }
        System.out.println();
    }

    /**
     * 打印双向链表
     * @param head 双向链表
     */
    public static void printDoubleList(DoubleNode head){
        DoubleNode end=null;
        System.out.println("the double linked list is: ");
        while (head!=null){
            System.out.print(head.data+" ");
            end=head;
            head=head.next;
        }
        System.out.println();

        while (end!=null){
            System.out.print(end.data+" ");
            end=end.pre;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        printList(head1);
        head1 = reverseList(head1);
        printList(head1);

        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.pre = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.pre = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.pre = head2.next.next;
        printDoubleList(head2);
        printDoubleList(reverseDoubleList(head2));
    }
}
