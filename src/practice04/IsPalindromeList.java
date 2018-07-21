package practice04;

import java.util.Stack;
import java.util.concurrent.TransferQueue;

/**
 * 题目：给定一个链表的头节点head， 请判断该链表是否为回文结构。
 * 例如： 1->2->1， 返回true。 1->2->2->1， 返回true。15->6->15， 返回true。 1->2->3， 返回false
 * <p>
 * 思路：思路一二用到了辅助空间，思路三没有用辅助空间
 * 思路一：
 * 利用栈。首先把链表遍历一遍，然后把便利的数都进栈；然后再遍历链表，每遍历一个从栈中弹出一个进行比较，
 * 全部一样返回true，否则返回false。
 * 思路二：
 * 还是利用栈。设置两个指针，一个快指针一次走两步，一个慢指针一次走一步，快指针走到尾，慢指针走到中间位置。
 * 然后把后半部分进栈，剩下的比较部分和思路一一样的。
 * 思路三：
 * 时间复杂度O(N),空间复杂度O(1)
 * 1.还是用到两个指针，一个快指针一次走两步，一个慢指针一次走一步，当快指针走到最后的时候，慢指针走到中间位置。
 * 2.把链表的后半部分进行逆序，把中间位置的节点指向空。
 * 3.一个从头开始遍历，一个从尾开始遍历，比较遍历的元素是否一样，当节点走到空停止；
 * 4.把链表后半部分的顺序再调整回来
 */
public class IsPalindromeList {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 思路一判断是否是回文链表
     *
     * @param head 链表
     * @return true or false
     */
    public static boolean isPalindromeList1(Node head) {
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.data != stack.pop().data) {
                return false;
            }
            head = head.next;
        }

        return true;
    }

    /**
     * 思路二判断链表是不是回文链表
     *
     * @param head 链表
     * @return true or false
     */
    public static boolean isPalindromeList2(Node head) {

        Stack<Node> stack = new Stack<Node>();
        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //把slow后面的节点都压栈
        while (slow.next != null) {
            slow = slow.next;
            stack.push(slow);
        }

        //遍历数组的前半部分，和栈中元素比较
        while (stack.size() != 0) {
            if (head.data != stack.pop().data) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 思路三判断链表是否是回文链表
     *
     * @param head 链表
     * @return true or false
     */
    public static boolean isPalindromeList3(Node head) {
        Node slow = head;
        Node fast = head;

        //找到链表的中间位置,slow所在的位置即是
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //把中间位置指向null，链表后半部分逆序
        Node pre = null;
        Node next = null;
//        Node cur=slow;
        while (slow != null) {
            next = slow.next;
            slow.next = pre;
            pre = slow;
            slow = next;
        }

        //分别从链表两边开始遍历，对比
        boolean flag = true;
        Node temp = pre;
        while (head != null) {
            if (head.data != pre.data) {
                flag = false;
                break;
            }
            head = head.next;
            pre = pre.next;
        }

        //把链表后半部分恢复回来
        pre = null;
        next = null;
        while (temp!=null){
            next=temp.next;
            temp.next=pre;
            pre=temp;
            temp=next;
        }

        return flag;

    }

    public static void main(String[] args) {
        Node head;
        head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(1);
//        head.next.next.next = new Node(2);
//        head.next.next.next.next = new Node(1);

        System.out.println(isPalindromeList3(head));
    }
}
