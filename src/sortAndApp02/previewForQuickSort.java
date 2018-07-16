package sortAndApp02;

/**
 * 快排的一个预习练习：
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数组的左边，
 * 大于num的数放在数组的右边
 */
public class previewForQuickSort {
    /**
     * 将数组arr中小于num的数放左边，大于num的数放右边
     * @param arr
     * @param num
     */
        public static void sortNum(int[] arr,int num){
            int less = -1;//用来记录小于num的数字的最后一个位置，他的下一个位置的数>=num
            for (int i=0;i<arr.length;i++){
                if(arr[i]<num){
                    swap(arr,i,less+1);
                    less++;
                }

            }

        }

        public static void swap(int[] arr,int i,int j){
            int temp =arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

    public static void main(String[] args) {
        int[] arr = {1,5,2,7,1,3,8,0,6};
        int num = 3;
        sortNum(arr,num);

        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
