package practice01;

import java.util.Arrays;

/*
 * 冒泡排序
 */
public class bubbleSort {

    public static void bubbleSort(int[] arr){
        //如果数组为空或者只有一个元素时，不用排序
        if(arr == null || arr.length<2)
            return;

        //如果数组中元素个数超过一个，可以进行排序
        for(int i = arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
               if(arr[j] >arr[j+1]) {
                   //交换
                    swap(arr,j,j+1);
               }
            }
        }

//        //打印数组
//        for(int i =0;i<arr.length;i++){
//            System.out.print(arr[i]+" ");
//        }

    }
    //交换数组中的两个元素
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * 对数器，模拟OJ测试编写的代码
     */
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //产生正数，负数，0
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

    public static void main(String[] args) {
        /*
         * 利用对数器测试所写方法的正确性
         */
        int testTime = 50;
        int maxSize = 100;
        int maxValue = 100;
        boolean flag = true;
        //测试50000次，如果某次测试不相等，则直接跳出循环
        for(int i=0;i<testTime;i++){
            int[] arr1 = generateRandomArray(maxSize,maxValue);
            int[] arr2 = copyArray(arr1);
            bubbleSort(arr1);
            comparator(arr2);
            if(!isEqual(arr1,arr2)){
                flag = false;
                break;
            }
        }//for
//        System.out.println(111111);
        System.out.println(flag ? "succeed" : "failed");
        /*
         * 经对数器测试，自己所写方法没问题
         */

        //查看自己所写方法的运行效果
        int[] arr = generateRandomArray(maxSize,maxValue);
        printArray(arr);
        bubbleSort(arr);
        printArray(arr);


    }
}
