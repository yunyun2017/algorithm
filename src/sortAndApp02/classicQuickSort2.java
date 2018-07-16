package sortAndApp02;


/**
 * 改进的快排
 * 思路：利用荷兰国旗问题的解决思路进行的改进。也是利用的数组最后一个元素作为划分的节点，
 * 将小的元素放数组左边，等于的放中间，大于的放数组右边，最后将最后一个元素与大于区域的第一个元素进行交换
 * 特点：每次可以将与数组最后一个元素相同的元素都确定好位置
 * <p>
 * 随机快排：
 * 每次都从数组中随机选择一个数和数组最后一个数进行交换，后面的思路和改进的快排一样，这样大概率可以避免划分点取得太偏
 */
public class classicQuickSort2 {
    public static void classicQuickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        classicQuickSort2(arr, 0, arr.length - 1);
    }

    public static void classicQuickSort2(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        //这行是随机快排的，从数组中随机选择一个元素和数组最后一个元素进行交换
        swap(arr, high, low + (int) Math.random() * (high - low + 1));

        int[] p = partition(arr, low, high);
        classicQuickSort2(arr, low, p[0] - 1);
        classicQuickSort2(arr, p[1] + 1, high);


    }

    /**
     * 对数组arr进行划分，找到存放 与最后一个元素相同的一系列元素的开始位置和结束位置
     * 返回：一个长度为2的数组，元素就是存放的起始位置
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static int[] partition(int[] arr, int low, int high) {
        int less = low - 1;
        int more = high;
        int cur = low;

        while (cur < more) {
            if (arr[cur] < arr[high]) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > arr[high]) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }

        //注意：将数组的最后一个元素和大区域的第一个元素交换,交换之后，more指向的是等于区域的最后一个元素
        swap(arr, more, high);

        return new int[]{++less, more};

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 5, 7, 1, 0, 8, 9};
        classicQuickSort2(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
