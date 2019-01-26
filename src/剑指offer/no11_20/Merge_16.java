package 剑指offer.no11_20;

import 剑指offer.commonWidgets.ListNode;

public class Merge_16 {

    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode p1 = list1, p2 = list2;
        ListNode mergeHead = null;

        if (p1.val < p2.val) {
            mergeHead = p1;
            mergeHead.next = Merge(p1.next, p2);
        } else {
            mergeHead = p2;
            mergeHead.next = Merge(p1, p2.next);
        }

        return mergeHead;
    }
}
