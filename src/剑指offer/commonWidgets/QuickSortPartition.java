package 剑指offer.commonWidgets;

import java.util.Random;

public class QuickSortPartition {
    /**
     * 从array中随机选择一个元素作为比较元素，小于它的在左边，大于它的在右边，最后返回存放这个比较数的位置
     * 此时该数组变成小于等于区域+大于区域，比较数已经放在它排序后的位置上了
     *
     * @param array 被划分的数组
     * @param start 开始位置
     * @param end   结束位置
     * @return 比较数存放的位置
     */
    public int Partition(int[] array, int start, int end) {
        swap(array, end, (int) Math.random() * (end - start + 1) + start);

        int less = start - 1;//小于等于区域的最后一个元素位置
        int cur = start;

        while (cur < end) {
            if (array[cur] <= array[end])
                swap(array, cur++, ++less);
            else
                cur++;
        }

        swap(array,++less,end);

        return less;
    }

    public void swap(int[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }
}
