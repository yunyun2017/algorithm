package 剑指offer.no21_30;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

/**
 * 思路一：
 * 复制过程分两步：第一步是复制原始链表上的每一个结点，并用next指针链接起来；
 * 第二步是设置每个结点的sibling指针。
 * 假设原始链表中的某个结点N的sibling指向结点S，由于S的位置在链表中可能在N前面，也可能在N后面，
 * 所以要定位S的位置需要从原始链表的头节点开始找。如果从原始链表的头结点开始沿着next经过s步找到结点S，则在
 * 复制链表中也要经过s步才能找到s'。  对于一个含有n个结点的链表，这种方式的实际复杂度为O(n2)
 * <p>
 * 思路二：
 * 思路一的时间主要是花费在定位结点的sibling上面，我们从这方面去优化。
 * 还是分两步：第一步复制原始链表上的每个结点N创建N'，然后把这些结点用next链接起来。
 * 同时，我们把<N,N'>的配对信息放到一个哈希表中。
 * 第二步：设置复制链表上每个结点的sibling。如果在原始链表中结点N的sibling指向结点S，则在复制链表中，对应的N'
 * 指向S'。由于有了哈希表，我们可以用O(1)时间根据S找到S'.  用空间换时间，空间O(N),时间O(N)
 * <p>
 * 思路三：
 * 不使用辅助空间的情况下实现O(N)的时间效率。
 * 第一步仍然是根据原始链表的每个结点N创建对应的N'，然后，把N'链接在N的后面。
 * 第二步设置复制出来的结点的sibling。原始链表上的N的sibling指向结点S，则复制出来的N'的sibling指向S'，也就是
 * 结点S的next指向的结点。
 * 第三步把这个长链表拆分成两个链表：把奇数位置的结点用next指针链接起来就是原始链表；偶数位置的结点用next链接起来
 * 就是复制出来的链表
 */
public class Clone_25 {
    public RandomListNode Clone(RandomListNode pHead) {
        cloneNode(pHead);
        clonedNodeRandom(pHead);
        return seperateLinklist(pHead);
    }

    //第一步：复制结点，并把每个复制出来的结点链接到原结点的后面
    public void cloneNode(RandomListNode pHead) {
        RandomListNode pNode = pHead;

        while (pNode != null) {
            RandomListNode cloneNode = new RandomListNode(pNode.label);
            cloneNode.next = pNode.next;
            cloneNode.random = null;

            pNode.next = cloneNode;
            pNode = cloneNode.next;
        }
    }

    //第二步：设置复制出来的结点的sibling。
    public void clonedNodeRandom(RandomListNode pHead) {
        RandomListNode pNode = pHead;

        while (pNode != null) {
            RandomListNode clonedNode = pNode.next;
            if (pNode.random != null)
                clonedNode.random = pNode.random.next;
            pNode = clonedNode.next;
        }
    }

    //第三步：拆分这个长链表为两个短链表，并将复制链表返回
    public RandomListNode seperateLinklist(RandomListNode pHead){
        RandomListNode pNode = pHead;
        RandomListNode cloneHead = null;
        RandomListNode cloneNode = null;

        if (pNode!=null){
            cloneHead = cloneNode = pNode.next;
            pNode.next = cloneNode.next;
            pNode = cloneNode.next;
        }

        while (pNode!=null){
            cloneNode.next = pNode.next;
            cloneNode = pNode.next;
            pNode.next = cloneNode.next;
            pNode = cloneNode.next;
        }

        return cloneHead;
    }
}
