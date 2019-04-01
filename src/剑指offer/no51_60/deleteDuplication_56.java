package 剑指offer.no51_60;

import 剑指offer.commonWidgets.ListNode;

public class deleteDuplication_56 {
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return null;

        ListNode pre = null, cur = pHead;
        while (cur != null) {
            ListNode next = cur.next;

            if (next != null && next.val == cur.val) {
                //删除
                int deleteVal = cur.val;
                ListNode toDel = cur;
                while (toDel != null && toDel.val == deleteVal) {
                    toDel = toDel.next;
                }
                if (pre == null) {
                    pHead = toDel;
                    cur = toDel;
                } else {
                    pre.next = toDel;
                    cur = toDel;
                }
            } else {
                pre = cur;
                cur = next;
            }
        }

        return pHead;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
//        head.next = null;
        head.next = new ListNode(1);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        deleteDuplication_56 del = new deleteDuplication_56();
        ListNode res = del.deleteDuplication(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;

        }
    }
}
