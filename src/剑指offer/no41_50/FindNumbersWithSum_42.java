package 剑指offer.no41_50;

import com.sun.jmx.snmp.internal.SnmpModelLcd;

import java.util.ArrayList;

/*
    思路：这题思路可以参考第41题，这是第41题的简化版
    数组头和尾分别设置一个指针，然后根据这两个指针指向的数字和是大于，小于还是等于s来分别移动
    头指针和尾指针。当大于时，尾指针向前移动；当小于时，头指针向后移动；当等于时，则找到这两个数字。
 */
public class FindNumbersWithSum_42 {
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> twoNum = new ArrayList<>();
        if (array.length == 0 || sum < array[0])
            return twoNum;

        int small = 0, big = array.length - 1;
        int mulResult = Integer.MAX_VALUE;
        while (small < big && array[small] < (1 + sum) / 2) {
            int culculateSum = array[small] + array[big];
            if (culculateSum < sum) {
                small++;
            } else if (culculateSum > sum) {
                big--;
            } else {
                int res = array[small] * array[big];
                if (mulResult > res){
                    twoNum.clear();
                    twoNum.add(array[small]);
                    twoNum.add(array[big]);
                    mulResult = res;
                }
                small++;
                big--;
            }
        }

        return twoNum;
    }
}
