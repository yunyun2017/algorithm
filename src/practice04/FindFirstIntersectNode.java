package practice04;

import java.util.HashSet;

/**
 * 题目：两个单链表相交的一系列问题
 * 在本题中，单链表可能有环，也可能无环.
 * 给定两个单链表的头节点 head1和head2，这两个链表可能相交，也可能不相交。
 * 请实现一个函数，如果两个链表相交，请返回相交的第一个节点；如果不相交，返回null即可。
 * 问题一：判断单链表是否有环
 * 问题二：两个无环单链表相交，返回相交的第一个节点
 * 问题三：两个有环单链表相交，返回相交的第一个节点
 * 一个有环，一个无环，这两个链表不可能相交
 * <p>
 * 问题一思路：
 * （用辅助结构-hashSet）遍历链表，每遍历一个，在hashSet中查找看有没有，没有就添加进hashSet，
 * 如果有的话，表示链表是有环的；如果到链表结束，在hashSet中都没找到重复的，则链表没有环。
 * （不用辅助结构）准备两个指针：慢指针每次走一步，快指针每次走两步，当两者汇合时，把快指针指向头结点，
 * 然后慢指针和快指针每次都走一步，当两者再次汇合时，该节点就是入环的第一个节点（这是结论，记住即可）
 * <p>
 * 问题二思路：
 * （用hashSet）思路同问题一思路
 * （不用hashSet）先获取两个链表的长度差值n，然后让长的链表先走n步，然后两个链表同时往后走，第一个汇合点既是所求点
 * <p>
 * 问题三思路：
 * 两个有环单链表相交，有三种情况:
 * 1. 两个链表各自为环，没有交点；
 * 2. 两个链表在非环处相交，即这两个链表共享一个环；
 * 思路：将入环点作为链表的结束节点，问题转换成问题二了
 * 3. 两个链表在环上相交
 * 遍历第一个环，如果在该环上找到了第二个环的入环节点，说明两个链表相交，返回第一个环（第二个环）的入环地址；
 * 否则，遍历完第一个环都没有找到第二个环的入环节点，说明两个链表没有交点，返回null
 */
public class FindFirstIntersectNode {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 问题一：用hashSet判断链表有没有环
     *
     * @param head 链表
     * @return null或者第一个入环节点
     */
    public static Node hasLoop1(Node head) {
        HashSet<Node> set = new HashSet<Node>();

        while (head != null) {
            if (set.contains(head)) {//有环
                return head;
            }
            set.add(head);
            head = head.next;
        }

        return null;
    }

    /**
     * 问题一：空间复杂度O(1)实现：判断单链表是否有环
     *
     * @param head 链表
     * @return null或者第一个入环节点
     */
    public static Node hasLoop2(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node fast = head.next.next;
        Node slow = head.next;

        while (slow != fast) {
            if (fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            } else {
                return null;
            }
        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    /**
     * 问题二：用hashSet判断两个无环链表是否相交
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return null或者第一个相交点
     */
    public static Node noLoopIntersect1(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        HashSet<Node> set = new HashSet<Node>();

        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (set.contains(head2)) {
                return head2;
            }
            head2 = head2.next;
        }

        return null;
    }

    /**
     * 问题二：空间复杂度为O(1)，判断两个无环链表是否相交
     *
     * @param head1 链表1
     * @param head2 链表2
     * @return null或者第一个相交点
     */
    public static Node noLoopIntersect2(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;//记录两个链表的长度差

        while (cur1 != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n--;
            cur2 = cur2.next;
        }
        //找出那个链表长，哪个链表短
        cur1 = n > 0 ? head1 : head2;   //cur1指向长链表
        cur2 = cur1 == head1 ? head2 : head1;//cur2指向短链表
        n = Math.abs(n);  //对差值取绝对值

        //让cur1先走n步
        while (n != 0) {
            cur1 = cur1.next;
            n--;
        }
        //cur1和cur2同时往后走，相交点就是所求点
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;

    }

    /**
     * 判断两个有环单链表是否相交
     *
     * @param head1 有环单链表
     * @param loop1 链表1的环
     * @param head2 有环单链表
     * @param loop2 链表2的环
     * @return null或者相交的第一个点
     */
    public static Node twoLoopIntersect(Node head1, Node loop1, Node head2, Node loop2) {
        if (loop1 == loop2) {//两个链表共享一个环
            Node cur1 = head1;
            Node cur2 = head2;
            int n = 0;

            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }

            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }

            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);

            while (n != 0) {
                cur1 = cur1.next;
                n--;
            }

            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {//不相交或者在环上相交
            Node cur = loop1.next;
            while (cur != loop1) {
                if (cur == loop2) {
                    return loop1;
                }
                cur = cur.next;
            }
            return null;
        }
    }

    /**
     * 打印单向链表
     *
     * @param head 单向链表
     */
    public static void printList(Node head) {
        System.out.println("the linked List is: ");
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1, head2;
        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

//        head2 = new Node(0);
//        head2.next = new Node(9);
//        head2.next.next = new Node(8);
//        head2.next.next.next = head1.next; // 8->2

        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6

//        printList(head1);
//        Node n = hasLoop1(head1);
//        Node n = hasLoop2(head1);
//        System.out.println(n == null ? null : n.data);
        System.out.println("====================");

//        Node m = noLoopIntersect1(head1, head2);
        Node loop1 = hasLoop2(head1);
        Node loop2 = hasLoop2(head2);
        Node m = twoLoopIntersect(head1,loop1, head2,loop2);
        System.out.println(m == null ? null : m.data);
        System.out.println("====================");


    }
}
