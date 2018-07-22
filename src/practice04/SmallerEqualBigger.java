package practice04;

/**
 * 题目：将单向链表按某值划分成左边小、 中间相等、 右边大的形式
 * 初级：给定一个单向链表的头节点head，节点的值类型是整型，再给定一个整数pivot。
 * 实现一个调整链表的函数，将链表调整为左部分都是值小于pivot的节点，中间部分都是值等于pivot的节点，
 * 右部分都是值大于pivot的节点。除这个要求外，对调整后的节点顺序没有更多的要求。
 * 例如：链表9->0->4->5->1，pivot=3。调整后链表可以是1->0->4->9->5，也可以是0->1->9->5->4。
 * 总之，满足左部分都是小于3的节点，中间部分都是等于3的节点（本例中这个部分为空），
 * 右部分都是大于3的节点即可。对某部分内部的节点顺序不做要求
 * <p>
 * 进阶：在原问题的要求之上再增加如下两个要求
 * 在左、中、右三个部分的内部也做顺序要求，要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一致。
 * 例如： 链表9->0->4->5->1， pivot=3。调整后的链表是0->1->9->4->5。
 * 在满足原问题要求的同时，左部分节点从左到右为0、1。在原链表中也是先出现0，后出现1；
 * 中间部分在本例中为空，不再讨论；右部分节点从左到右为9、4、5。在原链表中也是先出现9，然后出现4，最后出现5。
 * 要求：时间复杂度O(N)，空间复杂度O(1);
 * <p>
 * 思路：
 * 初级问题的思路：荷兰国旗问题的思路，然后进行相应的改动即可
 * 把链表中的所有节点都放到数组中去，然后利用荷兰国旗问题的思路对他们进行操作，操作完之后，
 * 再把数组中的所有元素串成链表即可
 * <p>
 * 进阶问题思路：
 * 相当于把这个链表拆分成了三个链表，分别为less，equal和more，分别表示小于，等于，大于的节点；
 * 遍历一遍，找到小于k的放less这里，等于k的放equal这里，大于k的放more这里
 * 注意第一次出现的小于k的，等于k的，大于k的这些节点的处理
 * 最后把less，equal，more这三个链表连接成一个链表即可
 */
public class SmallerEqualBigger {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 将链表head中小于k的放左边，等于的放中间，大于的放右边。各个部分的元素顺序没有要求
     *
     * @param head 链表
     * @param k    指定的数
     * @return 题目要求的链表
     */
    public static Node smallEqualBig1(Node head, int k) {
        if (head == null) {
            return head;
        }

        //遍历一遍链表，得到链表长度，知道开多大的数组
        Node cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }

        Node[] arr = new Node[len];
        for (int i = 0; i < len; i++) {
            arr[i] = head;
            head = head.next;
        }

        //对数组进行划分
        partitionArr(arr, k);

        //将数组各元素再连成链表结构
//        head = arr[0];
        for (int i = 0; i < len - 1; i++) {
            arr[i].next = arr[i + 1];
        }
        arr[len - 1].next = null;

        return arr[0];
    }

    public static void partitionArr(Node[] arr, int k) {
        int less = -1;
        int more = arr.length - 1;
        int cur = 0;

        while (cur <= more) {
            if (arr[cur].data < k) {
                swap(arr, cur++, ++less);
            } else if (arr[cur].data > k) {
                swap(arr, cur, more--);
            } else {
                cur++;
            }
        }

    }

    /**
     * 将链表head中小于k的放左边，等于的放中间，大于的放右边。各个部分的元素顺序相对于原始链表没有变化
     *
     * @param head
     * @param k
     */
    public static Node smallEqualBig2(Node head, int k) {
        Node lessHead = null, lessTail = null;
        Node equalHead = null, equalTail = null;
        Node moreHead = null, moreTail = null;

        //遍历一遍链表，找到小于k的放less这里，等于k的放equal这里，大于k的放more这里
//        Node cur = head;
        Node next = null;
        while (head != null) {
            //在给节点分类之前，先把每个节点都处理成独立的情况，即next都指向null
            next = head.next;
            head.next = null;
            if (head.data < k) {
                if (lessHead == null) {
                    lessHead = head;
                    lessTail = head;
                } else {
                    lessTail.next = head;
                    lessTail = lessTail.next;
                }
            } else if (head.data > k) {
                if (moreHead == null) {
                    moreHead = head;
                    moreTail = head;
                } else {
                    moreTail.next = head;
                    moreTail = moreTail.next;
                }
            } else {
                if (equalHead == null) {
                    equalHead = head;
                    equalTail = head;
                } else {
                    equalTail.next = head;
                    equalTail = equalTail.next;
                }
            }
            head = next;
        }

        /*//把less和equal连接
        if (lessTail != null) {
            lessTail.next = equalHead;
            equalTail = equalTail == null ? lessTail : equalTail;
        }
        //equal和more连接
        if (equalTail != null) {
            equalTail.next = moreHead;
        }

        return lessHead != null ? lessHead : equalHead != null ? equalHead : moreHead;*/

        //把less,more,equal连接起来
        if(lessHead==null){
            if(equalHead==null){
                return moreHead;
            }else{
                equalTail.next=moreHead;
                return equalHead;
            }
        }else{
            if(equalHead==null){
                lessTail.next=moreHead;
                return lessHead;
            }else{
                lessTail.next=equalHead;
                equalTail.next=moreHead;
                return lessHead;
            }
        }

    }

    /**
     * 交换两个节点
     *
     * @param arr 数组
     * @param m   数组的下标
     * @param n   数组的下标
     */
    public static void swap(Node[] arr, int m, int n) {
        Node temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }

    /**
     * 打印链表
     *
     * @param head 链表
     */
    public static void printList(Node head) {
        if (head == null) {
            return;
        }

        while (head != null) {
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);

        printList(head1);
//        head1 = smallEqualBig1(head1, 5);
        head1 = smallEqualBig2(head1, 9);
        printList(head1);
    }
}
