package sortAndApp02;

import sun.reflect.generics.tree.VoidDescriptor;

/**
 * 荷兰国旗问题：
 * 给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，
 * 等于num的数放在数组的中间，大于num的数放在数组的右边
 */
public class helanFlag {
    /**
     * 接收一个数组，对这个数组中的数进行处理，小的在左边，相等的在中间，大的在右边
     * @param arr
     * @param num
     */
    public static void helanFlag(int[] arr,int num){
        int less = -1;          //记录小于num的数字的最后一位，它的下一位>=num，less位置及比它小的位置都是小于num的
        int more = arr.length-1;//记录大于num的数字的前一位，在它之后的所有数字都是>num的
        int cur=0;

        while (cur<=more){
            if(arr[cur]<num){//与less+1位的元素交换
                swap(arr,cur,less+1);
                less++;
                cur++;
            }else if(arr[cur] == num){
                cur++;
            }
            else{
                swap(arr,cur,more);
                more--;
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {6,2,5,7,3,9,0,1,3,6,8,3,11,2};
        int num = 3;
        helanFlag(arr,num);

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }
}
