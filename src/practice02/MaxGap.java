package practice02;

/**
 * 题目：给定一个数组， 求如果排序之后， 相邻两数的最大差值， 要求时间复杂度O(N)， 且要求不能用非基于比较的排序
 * 思路：借用桶排序的思想，但是没有用桶排序
 * 步骤：
 * 1. 找到数组中的最小值min和最大值max，分别放在第0个桶和第n个桶
 * 2. 准备桶（数组中有n个数，准备n+1个桶，这样至少有一个空桶）
 * 设计空桶的原因：这样我们就保证了最大差值的相邻两数一定是来自不同的桶，而非桶内的两个数
 * 3. 记录进入该桶的元素信息：记录进入该桶所有数中的最小值和最大值，其他的数不记录；每个桶有标记，标记该桶是否是空桶
 * 4. 从第一个非空桶开始往后遍历，找到一个非空桶，再找到这个非空桶的前面最近的一个非空桶，
 * 用后一个非空桶的最小值-前一个非空桶的最大值，遍历完所有桶之后，一定可以找到题目所求的最大差值
 */
public class MaxGap {
    /**
     * 对数组arr排序后，找到相邻两数的最大差值
     *
     * @param arr 待操作数组
     */
    public static int findMaxGap(int[] arr) {
        if (arr == null || arr.length == 1) {
            return 0;
        }
        int arrayLen = arr.length;

        int min = arr[0], max = arr[0]; //记录数组的最小值和最大值
        //找到数组的最小值和最大值
        for (int i = 1; i < arrayLen; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }

        if (max == min) {
            return 0;
        }

        boolean[] hasNum = new boolean[arrayLen + 1];//标记每个桶有没有进过数，哪些是空桶
        int[] mins = new int[arrayLen + 1];//记录每个桶的最小值
        int[] maxs = new int[arrayLen + 1];//记录每个桶的最大值
        int n; //桶号

        //遍历一遍数组，判断数组中的元素分别属于哪个桶，然后更新对应桶的信息（hasNum,mins,maxs）
        for (int i = 0; i < arrayLen; i++) {
            n = findBucket(arr[i], arrayLen, min, max);

            //这个地方注意：更新桶信息之前，先判断这个桶是否是空桶，若是，则最小最大都是这个值；
            // 否则，最小最大可能变也可能不变
            mins[n] = hasNum[n] ? Math.min(mins[n], arr[i]) : arr[i];
            maxs[n] = hasNum[n] ? Math.max(maxs[n], arr[i]) : arr[i];
            hasNum[n] = true;
        }

        //开始求相邻两数的最大差值=后一个非空桶的最小值-前一个非空桶的最大值
        int lastMax = maxs[0];
        int maxGap = 0;

        for (int i = 1; i <= arrayLen; i++) {
            if (hasNum[i]) {
                maxGap = Math.max(maxGap, mins[i] - lastMax);
                lastMax = maxs[i];
            }
        }

        return maxGap;
    }

    /**
     * 找到num属于第几号桶
     *
     * @param num      待找的数
     * @param arrayLen 数组长度
     * @param min      数组最小值
     * @param max      数组最大值
     * @return 桶号
     */
    public static int findBucket(int num, int arrayLen, int min, int max) {
        return (int) ((num - min) * arrayLen / (max - min));
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 8, 3, 0, 4};
        int maxGap = findMaxGap(arr);
        System.out.println("相邻两数的最大差值为：" + maxGap);
    }
}
