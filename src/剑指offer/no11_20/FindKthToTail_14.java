package 剑指offer.no11_20;

import 剑指offer.ListNode;

import java.util.Stack;

public class FindKthToTail_14 {
    public ListNode FindKthToTail(ListNode head, int k) {
        //不改变原链表的数据结构
        //遍历一遍链表，将这些节点入栈，然后从栈里弹出第k个节点即所求
        if(head == null) return null;

        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode p = head;
        while(p != null){
            stack.push(p);
            p=p.next;
        }

        int index = 1;
        while(!stack.isEmpty() && index != k){
            stack.pop();
            index++;
        }
        if(stack.isEmpty()) return null;

        return stack.pop();
    }

    /**
     * 思路二：只遍历一遍链表，便得到倒数第k个结点，我们可以：
     * 定义两个指针，第一个指针从链表头指针开始遍历向前走k-1步，到达第k个结点，第二个指针保持不变；
     * 从第k步开始，第二个指针也开始从链表的头指针开始遍历。由于两个指针的距离保持在k-1，当第一个指针到达链表的
     * 尾结点时，第二个指针正好指向倒数第k个结点。
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail_2(ListNode head, int k) {
        if (head == null || k == 0) return null;
        ListNode beforeNode = head;
        ListNode afterNode = null;

        for (int i =0;i<k-1;i++){
            if (beforeNode.next == null) return null;
            beforeNode = beforeNode.next;
        }

        afterNode = head;
        while (beforeNode.next !=null){
            beforeNode = beforeNode.next;
            afterNode = afterNode.next;
        }

        return afterNode;
    }

}

