package 剑指offer.no21_30;

/**
 * 思路：举例分析数组的规律
 * 我们在累加子数组的和时，如果该和为负数，那么下一个数字不应该再累加到该和上面，此时要求的子数组应该从下一个数字
 * 开始。另外，我们还需要一个变量来记录遍历过程中的最大和。
 */
public class FindGreatestSumOfSubArray_30 {
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0)
            return 0;

        int subSum = 0;
        int greatSum = array[0];
        for (int i = 0; i < array.length; i++) {
            if (subSum <= 0) {
                subSum = array[i];
            } else {
                subSum += array[i];
            }
            greatSum = subSum > greatSum ? subSum : greatSum;
        }

        return greatSum;
    }

    public static void main(String[] args) {
        int[] arr = {6,-3,-2,7,-15,1,2,2};
        FindGreatestSumOfSubArray_30 greatNum = new FindGreatestSumOfSubArray_30();
        int result = greatNum.FindGreatestSumOfSubArray(arr);
        System.out.println(result);
    }
}
