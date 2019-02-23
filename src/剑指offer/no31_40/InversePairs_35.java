package 剑指offer.no31_40;

import javax.sound.midi.Soundbank;

/*
  思路：先把数组分隔成子数组（把数组分成前后两个数组，然后对前后这两个数组再继续分，直到每个数组只含一个元素为止），
  然后进行合并的过程。合并过程中，一边合并相邻的子数组，一边统计逆序对的数目。
  合并相邻的子数组就是对数组进行排序，排序过程就是归并排序的过程，因此可以基于归并排序来写代码。


  问题一：合并相邻子数组过程中，如何统计逆序对的数目
  假设：左边数组元素为：5，7；右边数组元素为4，6；，合并统计过程如下：
  1. p1指针指向元素7，p2指针指向元素6，p3指针指向辅助数组最后一个位置；
  2. 比较p1和p2指针指向元素7和6的大小，发现7>6，而6是右边数组的最大值，所以7大于右边数组的所有元素，
  因此和右边数组中每个元素都构成逆序对，逆序对数目为2.然后将元素7放到p3指向的位置，p3往前移一位，p1往前移一位；
  3. 比较p1和p2指向的元素5和6，发现5<6，没有逆序对，然后将元素6放到p3指向的位置，p3往前移一位，p2往前移一位；
  4. 一直比较下去，直至所有元素都按递增顺序放到辅助数组中了结束，此时也得到了合并相邻子数组过程中的逆序对数目。

 */
public class InversePairs_35 {
    public int InversePairs(int[] array) {
        if (array.length == 0)
            return 0;

        //申请个临时数组，用于存储排序后的数组
        int[] tempArray = new int[array.length];
        int count = computeInversePairs(array, tempArray, 0, array.length - 1);

        return count;
    }

    //基于归并排序统计逆序对数目
    public int computeInversePairs(int[] array, int[] tempArr, int low, int high) {
        if (low == high)
            return 0;

        int mid = (low + high) >> 1;
        int leftCount = computeInversePairs(array, tempArr, low, mid) % 1000000007;
        int rightCount = computeInversePairs(array, tempArr, mid + 1, high) % 1000000007;

        //开始合并两个数组，并且计算逆序对数目
        int p1 = mid, p2 = high, p3 = high;
        int count = 0;//记录逆序对数目
        while (p1 >= low && p2 > mid) {
            if (array[p1] > array[p2]) {
                count += p2 - mid;
                tempArr[p3--] = array[p1--];
                if (count > 1000000007)
                    count %= 1000000007;
            } else {
                tempArr[p3--] = array[p2--];
            }
        }

        while (p1 >= low) {
            tempArr[p3--] = array[p1--];
        }
        while (p2 > mid) {
            tempArr[p3--] = array[p2--];
        }

        //将辅助数组中排好序的元素复制到原数组中
        for (int i = low; i <= high; i++)
            array[i] = tempArr[i];


        return (leftCount + rightCount + count) % 1000000007;
    }

    public static void main(String[] args) {
        InversePairs_35 inver = new InversePairs_35();
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 0};
        int num = inver.InversePairs(arr);
        System.out.println(num);
    }
}
