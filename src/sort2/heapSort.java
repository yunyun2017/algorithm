package sort2;

import java.util.Arrays;

/**
 * 堆排序思路：
 * 1、建堆。将一个给定的数组调整成大根堆（从数组的第一个元素开始，依次往后进行调整）
 * 2、排序：每次把二叉树的根结点去掉，放入数组的high位置，然后把二叉树的最后一个叶子结点作为根结点，
 * 然后再调整成大根堆。再重复上面的过程，直到二叉树中只有一个结点，将该结点直接放入数组中
 */
public class heapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //现在将数组看成完全二叉树,目标是：将数组arr建立成大根堆（从上往下）
        for (int i = 0; i < arr.length; i++) {
            createHeap(arr, i);
        }

        //接下来开始进行堆排序
        int heapSize = arr.length - 1;//用于记录当前堆的大小
        //将堆顶元素与堆的最后一个元素进行交换，堆的大小减1，然后再将剩余元素调整成堆，以此类推，直至堆的大小减小到0
        swap(arr, 0, heapSize--);
        while (heapSize > 0) {
            //调整成大顶堆
            heapify(arr, 0, heapSize);
            //将堆顶和堆最后一个元素进行交换
            swap(arr, 0, heapSize--);
        }


    }

    /**
     * 将数组arr中0-heapsize的元素调整成大根堆（从上往下调整）
     *
     * @param arr
     * @param heapSize
     */
    public static void heapify(int[] arr, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left <= heapSize) {
            //找出两个孩子中最大的那个
            int large = (left + 1 <= heapSize) && arr[left] < arr[left + 1] ? left + 1 : left;
//            if(arr[large]>arr[index]){
//                swap(arr,large,index);
//                index = large;
//            }else{
//                break;
//            }

            //或者如下写：两个孩子中的最大的和根结点比较，找出最大的
            large = arr[large] > arr[index] ? large : index;
            if (large == index) {
                break;
            }
            swap(arr, large, index);
            index = large;
            left = index * 2 + 1;
        }
    }

    /**
     * 将数组arr中index及之前的元素调整成大根堆
     *
     * @param arr
     * @param index
     */
    public static void createHeap(int[] arr, int index) {

        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    //    public static void main(String[] args) {
//        int[] arr = {1, 3, 2, 0, 5, 4};
//        heapSort(arr);
//
//        for (int j = 0; j < arr.length; j++) {
//            System.out.print(arr[j] + " ");
//        }
//    }
// for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        heapSort(arr);
        printArray(arr);
    }
}
