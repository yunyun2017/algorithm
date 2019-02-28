package 剑指offer.no31_40;

/*
    思路：看到这个数组是排序数组，于是很容易想到二分查找。
    但是二分查找得到的数组k可能是第一个，中间或者最后一个，位置不确定。
    所以我们可以用二分查找第一个k所在的位置，以及最后一个k所在的位置，这样就可以确定元素k的个数了
 */
public class GetNumberOfK_37 {
    public int GetNumberOfK(int[] array, int k) {
        if (array.length == 0)
            return 0;
        int first = getFirstOfK(array, k, 0, array.length - 1);
        int last = getLastOfK(array, k, 0, array.length - 1);

        if (first != -1)
            return last - first + 1;
        else
            return 0;

    }

    public int getFirstOfK(int[] array, int k, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) >> 1;
        if (array[mid] > k) {
            return getFirstOfK(array, k, start, mid - 1);
        } else if (array[mid] < k) {
            return getFirstOfK(array, k, mid + 1, end);
        } else if (mid - 1 >= 0 && array[mid - 1] == k) {
            return getFirstOfK(array, k, start, mid - 1);
        } else {
            return mid;
        }
    }

    public int getLastOfK(int[] array, int k, int start, int end) {
        if (start > end)
            return -1;

        int mid = (start + end) >> 1;
        if (array[mid] > k) {
            return getLastOfK(array, k, start, mid - 1);
        } else if (array[mid] < k) {
            return getLastOfK(array, k, mid + 1, end);
        } else if (mid + 1 <= array.length - 1 && array[mid + 1] == k) {
            return getLastOfK(array, k, mid + 1, end);
        } else {
            return mid;
        }
    }
}
