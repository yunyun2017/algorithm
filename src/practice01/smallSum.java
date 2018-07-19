package practice01;

/**
 * 归并排序的应用问题：
 * 小和问题
 * 在一个数组中，每一个数的左边比当前数小的数累加起来，叫做这个数组的小和
 * 举例：{1,2,4,7,3,9,8}，则小和为：1+1+2+1+2+4+1+2+1+2+4+7+3+1+2+4+7+3=
 * 最容易想到的：将数组中各元素遍历一遍，利用两个for循环解决，时间复杂度O（n^2）
 * 利用归并排序的思想解决：
 * 对于每个数，找该数右边比它大的数有几个，则该数乘以这个个数
 */
public class smallSum {
//    static int smallSum = 0;

    /**
     * 求数组arr的小和问题
     *
     * @param arr
     */
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return smallSum(arr, 0, arr.length - 1);
    }

    public static int smallSum(int[] arr, int low, int high) {
        if (low == high) {
            return 0;
        }
        int mid = (low + high) / 2;

        return smallSum(arr, low, mid) + smallSum(arr, mid + 1, high) + merge(arr, low, mid, high);

//        smallSum(arr, low, mid);
//        smallSum(arr, mid + 1, high);
//
//        /**
//         * 将左右两边进行合并，然后统计小和
//         */
//        merge(arr, low, mid, high);
    }

    public static int merge(int[] arr, int low, int mid, int high) {
        int a = low;
        int b = mid + 1;
        int[] temp = new int[high - low + 1];
        int i = 0;
        int smallSum = 0;

        while (a <= mid && b <= high) {
            smallSum += arr[a] < arr[b] ? arr[a] * (high - b + 1) : 0;
            temp[i++] = arr[a] < arr[b] ? arr[a++] : arr[b++];
        }

        while (a <= mid) {
            temp[i++] = arr[a++];
        }
        while (b <= high) {
            temp[i++] = arr[b++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[low + j] = temp[j];
        }

        return smallSum;

    }

    public static void main(String[] args) {
        int[] arr = {1,2,4,7,3,9,8};
        int smallSum = smallSum(arr);
        System.out.println("smallSum:" + smallSum);
        System.out.println("sum="+(1+1+2+1+2+4+1+2+1+2+4+7+3+1+2+4+7+3));
    }

}
