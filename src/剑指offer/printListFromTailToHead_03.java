package 剑指offer;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

/**
 * 1、遍历一遍链表，把每个节点放入栈中，然后把栈里面的所有元素都pop出来，得到一个从尾到头的链表
 * 2、遍历一遍链表，把该链表的next指向反向调整，这样遍历完这个链表即将这个链表逆置了，然后再遍历一遍该链表即可
 */
public class printListFromTailToHead_03 {
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> list = new ArrayList<Integer>();

        if (listNode == null) return list;

        ListNode p1 = null, p2 = listNode;
        ListNode p3 = p2.next;

        while (p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p2.next;
        }

        p2.next = p1;
        p1 = p2;

        while (p1 != null) {
            list.add(p1.val);
            p1 = p1.next;
        }

        return list;
    }

    public static void main(String[] args) {
        ListNode list = null;

        System.out.println(printListFromTailToHead(list));
    }
}
