package 剑指offer.no11_20;

import 剑指offer.commonWidgets.ListNode;

public class ReverseList_15 {
    public ListNode ReverseList(ListNode head) {
        if (head == null) return null;

        ListNode p1 = null;
        ListNode p2 = head;
        ListNode p3 = p2.next;
        while (p3 != null) {
            p2.next = p1;
            p1 = p2;
            p2 = p3;
            p3 = p3.next;
        }
        p2.next = p1;
        p1 = p2;
        p2 = p3;

        return p1;
    }
}
