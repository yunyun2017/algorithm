package sort1;

/**
 * 选择排序
 */
public class selectionSort {
    /**
     *
     * @param arr
     */
    public static void insertSort(int[] arr){

        if(arr == null || arr.length==1){
            //数组为空或者只有一个元素,不用排序
            return;
        }
        for(int i=0;i<arr.length;i++){
            int minIndex = i;
            for(int j=i+1;j<arr.length;j++){
                //记录最小元素
                minIndex = arr[minIndex] > arr[j] ? j: minIndex;

            }
            //一趟结束，找到本趟的最小元素，与第i个元素进行交换
            swap(arr, i, minIndex);
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

    /**
     * 交换数组中的两个元素
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] =temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,5,6,3,1,8,0,9,7};
        insertSort(arr);
    }
}
