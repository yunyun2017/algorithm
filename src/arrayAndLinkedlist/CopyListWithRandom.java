package arrayAndLinkedlist;

import java.util.HashMap;

/**
 * 题目：复制含有随机指针节点的链表
 * 初级：
 * 一种特殊的链表节点类描述如下：
 * public class Node { public int value; public Node next; public
 * Node rand;
 * public Node(int data) { this.value = data; }}
 * Node类中的value是节点值，next指针和正常单链表中next指针的意义一样，都指向下一个节点，
 * rand指针是Node类中新增的指针，这个指针可能指向链表中的任意一个节点， 也可能指向null。
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表中所有结构的复制，
 * 并返回复制的新链表的头节点。
 * 进阶：
 * 不使用额外的数据结构，只用有限几个变量，且在时间复杂度为 O(N)内完成原问题要实现的函数
 * <p>
 * 思路：
 * 初级思路：
 * 用到哈希表hashMap结构作为辅助。hashMap里的key存放的是原链表节点，value存放的是复制的节点
 * 1.遍历一遍链表，把原节点和复制节点存到hashMap中去；
 * 2.再遍历一遍链表，根据原节点，从hashMap中找到复制节点，根据原节点的next指针确定复制节点的next指针指向；
 * 根据rand指针找到它指向的原节点，根据这个原节点到hashMap中找到复制节点，连接上；
 * 3.返回复制链表的头结点
 * <p>
 * 进阶思路：
 * 1.将链表遍历一遍，把链表结构改为：原节点-复制节点-原节点-复制节点...这样的结构
 * 2.在新结构上，把复制节点的rand指针都连接上
 * 3.从新结构上把复制的链表和原链表分离出来，然后返回。
 */
public class CopyListWithRandom {
    /**
     * 定义特殊的链表结构
     */
    public static class Node {
        private int data;
        private Node next;
        private Node rand;

        private Node(int data) {
            this.data = data;
        }
    }

    /**
     * 初级思路复制链表
     *
     * @param head 链表
     * @return 复制出来的链表
     */
    public static Node copyList1(Node head) {
        HashMap<Node, Node> map = new HashMap<Node, Node>();

        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.data));
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }

        return map.get(head);
    }

    /**
     * 进阶思路复制链表
     *
     * @param head 链表
     * @return 复制出来的链表
     */
    public static Node copyList2(Node head) {
        if(head==null){
            return head;
        }

        Node cur = head;
        Node next = null;
        //1.创建了一个 原节点-复制节点 的这种链表结构
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.data);
            cur.next.next = next;
            cur = next;
        }

        //2.将复制节点的rand指针连接上
        cur = head;
        Node copyNode = null;
        while (cur != null) {
            copyNode = cur.next;
            copyNode.rand = cur.rand != null ? cur.rand.next : null;
            cur = copyNode.next;
        }

        //把原节点链表和复制节点链表分离开
        cur=head;
        Node res=cur.next;
        while (cur!=null){
            copyNode=cur.next;
            next=cur.next.next;
            cur.next=next;
            copyNode.next=next!=null?next.next:null;
            cur=next;
        }

        return res;
    }

    public static void printRandLinkedList(Node head) {
        Node cur = head;
        System.out.print("order: ");
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
        cur = head;
        System.out.print("rand:  ");
        while (cur != null) {
            System.out.print(cur.rand == null ? "- " : cur.rand.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = null;
        Node res1 = null;
        Node res2 = null;
        printRandLinkedList(head);
//        res1 = copyList1(head);
//        printRandLinkedList(res1);
        res2 = copyList2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
//        res1 = copyList1(head);
//        printRandLinkedList(res1);
        res2 = copyList2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

    }
}
