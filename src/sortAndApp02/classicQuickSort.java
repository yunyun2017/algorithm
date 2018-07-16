package sortAndApp02;

/**
 * 经典快排：
 * 思路：每次用数组的最后一个元素作为划分的节点，小于等于该元素的放数组左边，
 * 大于该元素的放数组右边，最后将该元素放数组中间
 * 特点：每次排序只能确定一个元素
 */
public class classicQuickSort {
    public static void classicQuickSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        classicQuickSort(arr, 0, arr.length - 1);
    }

    public static void classicQuickSort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        //找到 存放划分点 的位置
        int p = partition(arr, low, high);
         /* 找到存放划分点的位置后，就要把划分点交换到它的正确位置上来，然后在将划分点左边和划分点右边进行快排。
         如果不是这个顺序，则最后结果是不正确的
         */
        swap(arr, p, high);

        classicQuickSort(arr, low, p - 1);
        classicQuickSort(arr, p + 1, high);


    }

    public static int partition(int[] arr, int low, int high) {
        int less = low - 1;
        int cur = low;

        while (cur < high) {
            if (arr[cur] <= arr[high]) {
                swap(arr, cur++, ++less);
            } else {
                cur++;
            }
        }

        return ++less;
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 7, 1, 0, 8, 9};
        classicQuickSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

}
