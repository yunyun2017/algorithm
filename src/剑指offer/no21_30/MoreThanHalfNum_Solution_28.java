package 剑指offer.no21_30;

import 剑指offer.commonWidgets.QuickSortPartition;

/**
 * 思路一：基于Partition函数的O(n)算法
 * 题意中说明：数组中有一个数字出现的次数超过了数组长度的一半。如果把这个数组排序，那么排序后位于数组中间的
 * 数字一定就是那个出现次数超过数组长度一半的数字。也就是说：这个数字就是统计学上的中位数，即长度为n的数组中
 * 第n/2大的数字。题目即转换为：求符合题意的中位数。
 * 结合快排思想考虑，在快排中，如果选中的数字下标刚好是n/2，那么这个数字就是数组的中位数。如果它的下标大于n/2，
 * 则中位数在它的左边，我们可以继续在它的左边部分的数组中进行查找；反之，则中位数在它的右边，我们在它的右边部分
 * 的数组中进行查找。明显，这是一个递归过程
 * <p>
 * 思路二：根据数组特点分析出的O(n)算法
 * 题目说：数组中有一个数字出现的次数超过数组长度的一半，也就是说，它出现的次数比其他所有数字出现的次数和还要多。
 * 因此，我们考虑在遍历数组时保存两个值：一个是数组中的一个数字，一个是次数。当我们遍历到下一个数字的时候，如果
 * 下一个数字和我们之前保存的数字相同，则次数加1；如果下一个数字和我们之前保存的数字不同，则次数减1.如果次数为0，
 * 我们需要保存下一个数字，并把次数设为1.
 * 这样，最后我们要找的数字肯定是最后一次把次数设为1时对应的数字。
 */
public class MoreThanHalfNum_Solution_28 {
    //思路一
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0)
            return 0;

        int middle = array.length >> 1;
        int start = 0;
        int end = array.length - 1;
        QuickSortPartition part = new QuickSortPartition();
        int pos = part.Partition(array, start, end);

        while (pos != middle) {
            if (pos > middle) {
                end = pos - 1;
                pos = part.Partition(array, start, end);
            } else {
                start = pos + 1;
                pos = part.Partition(array, start, end);
            }
        }

        //检查这个中位数出现的次数是否超过数组长度的一半
        int result = array[middle];
        if (!CheckMoreThanHalf(array, result))
            return 0;
        return result;
    }

    public boolean CheckMoreThanHalf(int[] array, int num) {
        int times = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == num)
                times++;
        }

        return times * 2 > array.length;
    }

    //思路二
    public int MoreThanHalfNum_Solution_2(int[] array) {
        if (array.length == 0)
            return 0;

        int result = array[0];
        int times = 1;
        for (int i = 1; i < array.length; i++) {
            if (times > 0) {
                if (array[i] == result)
                    times++;
                else
                    times--;
            } else {
                result = array[i];
                times = 1;
            }
        }

        //检查这个result出现的次数是否超过数组长度的一半
        if (!CheckMoreThanHalf(array, result))
            return 0;

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 2, 2, 2, 5, 4, 2};

        MoreThanHalfNum_Solution_28 m = new MoreThanHalfNum_Solution_28();
        int result = m.MoreThanHalfNum_Solution_2(arr);
        System.out.println(result);
    }
}
