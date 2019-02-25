package 剑指offer.no31_40;

import 剑指offer.commonWidgets.ListNode;

/*
    思路一：分三种情况进行讨论
    1. 至少一条链表为null的情况：
        那这两条链表就没有公共结点
    2. 两条链表都不为空，并且两条链表不相交：
        那这两条链表就没有公共结点
    3. 两条链表有公共结点，分两种情况进行讨论：
        （1）两条链表完全重合，变成一条链表：第一个公共结点就是头结点
        （2）两条链表在中间重合，那么不重合的结点个数可能相同，也可能不同：
            先分别遍历两条链表，确定两个链表的长度差，设为m，然后让长链表先走m步；
            两条链表同时往后走，第一个重合的结点就是第一个公共结点。
 */
public class FindFirstCommonNode_36 {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        if (pHead1 == pHead2)
            return pHead1;

        ListNode p1 = pHead1, p2 = pHead2;
        int len1 = 0, len2 = 0;
        while (p1 != null && p2 != null) {
            len1++;
            len2++;
            p1 = p1.next;
            p2 = p2.next;
        }

        while (p1 != null) {
            len1++;
            p1 = p1.next;
        }

        while (p2 != null) {
            len2++;
            p2 = p2.next;
        }

        //得到了两条链表的长度
        p1 = pHead1;
        p2 = pHead2;

        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                p1 = p1.next;
            }
        } else if (len1 < len2) {
            for (int i = 0; i < len2 - len1; i++) {
                p2 = p2.next;
            }
        }

        while (p1 != null && p2 != null) {
            if (p1 == p2)
                return p1;
            p1 = p1.next;
            p2 = p2.next;
        }

        return null;
    }
}
