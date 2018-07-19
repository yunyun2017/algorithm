package practice01;

/**
 * 归并排序
 * 利用的是分治的思想：
 * 将一个数组左侧排好序，右侧排好序，再将这两个排好序的数组利用外排进行排序
 */
public class mergeSort {
    /**
     * 对数组arr进行归并排序
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length == 1) {
            return;
        }

        mergeSort(arr, 0, arr.length - 1);


    }

    /**
     * 重载mergeSort方法
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void mergeSort(int[] arr, int low, int high) {
        if (low == high) {
            return;
        }

        int mid = (low + high) / 2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        //利用外排方式将两个排好序的数组进行合并
        merge(arr, low, mid, high);
    }

    /**
     * 将数组arr中的low-mid和mid+1-high利用外排方式进行排序
     *
     * @param arr
     * @param low
     * @param mid
     * @param high
     */
    public static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];//辅助数组
        int a = low;
        int b = mid + 1;
        int i = 0;
        while (a <= mid && b <= high) {
            if (arr[a] <= arr[b]) {
                temp[i++] = arr[a++];
            } else {
                temp[i++] = arr[b++];
            }

            //更简洁的写法
//            temp[i++]= arr[a]<=arr[b]?arr[a++]:arr[b++];
        }
        while (a <= mid) {
            //将数组中剩余元素都拷贝到temp数组中去
            temp[i++] = arr[a++];
        }
        while (b <= high) {
            temp[i++] = arr[b++];
        }

        //将辅助数组中的元素拷贝到原数组中
        for (int j = 0; j < temp.length; j++) {
            arr[low + j] = temp[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 7, 5, 8, 6, 0, 7, 9, 4};

        mergeSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
