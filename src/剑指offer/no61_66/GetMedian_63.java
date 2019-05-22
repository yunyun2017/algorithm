package 剑指offer.no61_66;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
    思路：将这个数据队列分成两部分，数据较小的在队列左边，数据较大的在队列右边。计算中位数时，
    分别取左边的最大值和右边的最小值，至于左右两边的数据队列是否是排序的，这个不做要求。
    我们选择堆这种数据结构来实现这个要求：左边用到大顶堆，右边用到小顶堆。

    插入时：
    1、当前插入总数为偶数情况时：此时这个新数据应该插入小顶堆中，但是需要先经过大顶堆筛选出最大的数，然后插入小顶堆中；
    2、当前插入总数为奇数情况时：跟上面的情况正好相反

    取中位数时：
    1、当前有偶数个：两个堆顶元素的中位数
    2、当前有奇数个：小顶堆的堆顶元素为中位数
 */
public class GetMedian_63 {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(5, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    });
    int count = 0;  //插入的总量

    public void Insert(Integer num) {
        if ((count & 1) == 0) {
            //当前插入总数为偶数，需要插入小顶堆中
            maxHeap.offer(num);
            int maxNum = maxHeap.poll();
            minHeap.offer(maxNum);
        } else {
            minHeap.offer(num);
            int minNum = minHeap.poll();
            maxHeap.offer(minNum);
        }
        count++;
    }

    public Double GetMedian() {
        if ((count & 1) == 0) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.peek() / 1.0;
        }
    }
}
