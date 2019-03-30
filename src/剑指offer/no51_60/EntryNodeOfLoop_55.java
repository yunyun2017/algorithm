package 剑指offer.no51_60;

import 剑指offer.commonWidgets.ListNode;

import javax.sound.midi.Soundbank;

/*
    问题：给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null
    分析：该问题可以拆成两个问题：
        问题一：给一个链表，判断该链表是否有环；
        问题二：如果该链表有环，找出环的入口结点；
    解决：
    问题一思路：设置两个指针，一个指针走两步，一个指针走一步，如果两个指针最终相聚，则该链表有环。并且，
    相聚的点一定在环内。从此点出发，走一圈回到该点，可以算出来环包含的结点个数；

    问题二思路：同样设置两个指针指向头结点，假设环包含的结点个数为：n个，让其中一个指针先走n步，然后两个指针
    同时往后走，相聚的结点即为环的入口结点
 */
public class EntryNodeOfLoop_55 {
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null)
            return null;

        ListNode interNode = hasLoop(pHead);
        if (interNode == null)
            return null;

        //算出环包含的结点个数
        ListNode p = interNode.next;
        int count = 0;
        while (p != interNode) {
            count++;
            p = p.next;
        }
        count++;

        return findEntry(pHead, count);
    }

    //判断链表是否有环，有环则返回相交的结点，无环则返回null
    public ListNode hasLoop(ListNode pHead) {
        ListNode p1 = pHead, p2 = pHead;

        while (p2 != null) {
            p1 = p1.next;
            if (p2.next != null) {
                p2 = p2.next.next;
            } else {
                return null;
            }

            if (p1 == p2) {
                return p1;
            }
        }

        return null;
    }

    //找到环的入口
    public ListNode findEntry(ListNode pHead, int count) {
        ListNode p1 = pHead, p2 = pHead;

        for (int i = 0; i < count; i++) {
            p2 = p2.next;
        }

        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;

    }

    public static void main(String[] args) {
        ListNode p = new ListNode(1);
        p.next = new ListNode(2);
        p.next.next = new ListNode(3);
        p.next.next.next = new ListNode(4);
        p.next.next.next.next = new ListNode(5);
        p.next.next.next.next.next = new ListNode(6);
        p.next.next.next.next.next.next = p.next.next;

        EntryNodeOfLoop_55 entry = new EntryNodeOfLoop_55();
        ListNode node = entry.EntryNodeOfLoop(p);
        System.out.println(node.val);

    }
}
