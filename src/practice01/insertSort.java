package practice01;

/**
 * 插入排序，实际应用中还有用处，而冒泡和选择排序现在实际应用中基本已不用了
 */
public class insertSort {

    /**
     * 对数组arr进行插入排序
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if(arr == null || arr.length ==1){
            return;
        }
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0;j--){
                if(arr[j] > arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }

        //打印数组看结果
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {3,2,4,6,1,0,9,5,5};
        insertSort(arr);
    }
}

