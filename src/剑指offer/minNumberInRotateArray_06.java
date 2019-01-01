package 剑指offer;

/**
 * 思路一：用两个指针，一个指向数组第一个元素，一个指向数组最后一个元素，然后两个指针分别往数组中间移动；
 * 若数组前面的元素小于后面的元素，表示已经找到了该数组的最小元素。最小元素要不就是本次找到的最小元素，要不就是上次查找过程中的最小元素
 * <p>
 * 思路二：只要是排序数组（或者是在一定程度上排好序的数组）都可以用二分查找法实现O（logn）的查找。本题也可以用二分查找法的思路来寻找这个
 * 最小的元素
 */
public class minNumberInRotateArray_06 {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }

//        int lastMin = array [array.length-1];
//        int i ,j;
//        for(i = 0,j = array.length-1;i< array.length&&j>=0;i++,j--){
//            if(array[i] < array[j]){
//                break;
//            }else{
//                lastMin = array[j];
//            }
//        }
//
//        return lastMin < array[i]? lastMin: array[i];
        int leftIndex = 0, rightIndex = array.length - 1;
        int midIndex = leftIndex;

        while (array[leftIndex] >= array[rightIndex]) {
            if (rightIndex - leftIndex == 1) {
                midIndex = rightIndex;
                break;
            }

            midIndex = (leftIndex + rightIndex) / 2;

//            左右和中间三个元素相等，则无法判断数组最小元素位于中间元素的左边还是右边，此时只能用顺序查找
            if (array[leftIndex] == array[rightIndex] && array[leftIndex] == array[midIndex]) {
                //顺序查找
                return minInOrder(array, leftIndex, rightIndex);
            }

            if (array[midIndex] > array[leftIndex]) {
                leftIndex = midIndex;
            }

            if (array[midIndex] < array[rightIndex]) {
                rightIndex = midIndex;
            }
        }

        return array[midIndex];
    }

    public int minInOrder(int[] array, int leftIndex, int rightIndex) {
        int i = leftIndex + 1;
        int minNum = array[leftIndex];
        while (i <= rightIndex) {
            minNum = minNum > array[i] ? array[i] : minNum;
            i++;
        }

        return minNum;
    }

    public static void main(String[] args) {
        int[] array = {1, 0, 1, 1, 1};
//        int[] array1 = {1, 1, 1, 0, 1};
        int[] array1 = {};

        minNumberInRotateArray_06 mn = new minNumberInRotateArray_06();
        int result = mn.minNumberInRotateArray(array);
        int result1 = mn.minNumberInRotateArray(array1);
        System.out.println("result:" + result);
        System.out.println("result1:" + result1);
    }
}

