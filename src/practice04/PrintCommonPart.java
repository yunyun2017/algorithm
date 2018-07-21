package practice04;

/**
 * 题目：给定两个有序链表的头指针head1和head2， 打印两个链表的公共部分
 * <p>
 * 思路：思路和外排的思路类似，从两个链表头部开始，如果两个相等，在打印出来，然后两个都往后走；
 * 否则，哪个链表元素小，则哪个链表指针往后走，直至某一方或双方都走到最后，结束
 */
public class PrintCommonPart {
    public static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    /**
     * 打印两个有序链表head1和head2的公共部分
     * @param head1
     * @param head2
     */
    public static void printCommonPart(Node head1,Node head2){
        if(head1==null || head2==null){
            return;
        }

        while (head1!=null && head2!=null){
            if(head1.data == head2.data){
                System.out.print(head1.data+" ");
                head1=head1.next;
                head2=head2.next;
            }else if(head1.data>head2.data){
                head2=head2.next;
            }else{
                head1=head1.next;
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(2);
        node1.next = new Node(3);
        node1.next.next = new Node(5);
        node1.next.next.next = new Node(6);

        Node node2 = new Node(1);
        node2.next = new Node(2);
        node2.next.next = new Node(5);
        node2.next.next.next = new Node(7);
        node2.next.next.next.next = new Node(8);

        printCommonPart(node1,node2);
    }
}
