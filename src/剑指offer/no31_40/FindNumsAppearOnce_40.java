package 剑指offer.no31_40;

import java.util.HashMap;

/*
    思路：同34题的思路，借助hashmap来存储每个数字出现的次数

    思路二：考虑到题目中提到，除了两个只出现一次的数字，其他的数字都是出现两次的，于是我们想到异或运算的一个性质：
    任何一个数字异或它自己都是0.也就是说，如果我们从头到尾依次异或数组中的每一个数字，那么最终的结果刚好是那个只
    出现一次的数字，因为那些成对出现两次的数字全部在异或中抵消了。

    对于这个题目，我们的思路是：
    想办法将这个数组分成两个子数组，每个子数组都包含一个只出现一次的数字，其他数字都是成对出现两次的。然后我们按照
    上面提到的方法，就可以分别找到两个只出现一次的数字了。
    步骤：
    1、我们从头到尾依次异或数组中的每个数字，最终结果就是两个只出现一次的数字的异或结果。
    2、显然，这个异或结果不为0.我们在这个结果中找到从右边数第一个为1的位的位置，记为第n位。
    3、我们以第n位是否为1将原数组分为两个子数组，第一个子数组中每个数字的第n位都为1，第二个子数组中每个数字的第n位都
    为0.这样我们的两个子数组中分别包含一个只出现一次的数字，其余数字都是成对出现两次的。
    4、针对这两个子数组，应用上面提到的方法，可以分别找出两个只出现一次的数字。


 */
public class FindNumsAppearOnce_40 {
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        if (array.length == 0)
            return;

        HashMap<Integer, Integer> appearMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < array.length; i++) {
            if (appearMap.containsKey(array[i])) {
                int count = appearMap.get(array[i]);
                appearMap.put(array[i], ++count);
            } else {
                appearMap.put(array[i], 1);
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (appearMap.get(array[i]) == 1) {
                if (num1[0] == 0) {
                    num1[0] = array[i];
                } else {
                    num2[0] = array[i];
                }
            }
        }

    }

    public void FindNumsAppearOnce2(int[] array, int num1[], int num2[]) {
        if (array.length < 2)
            return;

        int ORresult = 0;//设为0原因：任何数和0异或都是它本身
        for (int i = 0; i < array.length; i++) {
            ORresult ^= array[i];
        }

        //异或结果中找到从右边数第一个1的位的位置
        int index = findFirstOf1(ORresult);

        num1[0] = num2[0] = 0;
        for (int i = 0; i < array.length; i++) {
//            int tmp = array[i];
            if (is1OfBit(array[i], index)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    public int findFirstOf1(int n) {
        int index = 0;
        while ((n & 1) == 0) {
            n = n >> 1;
            index++;
        }

        return index;
    }

    public boolean is1OfBit(int n, int index) {
        n = n >> index;

        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        FindNumsAppearOnce_40 find = new FindNumsAppearOnce_40();

        int[] array = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        find.FindNumsAppearOnce2(array, num1, num2);
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }
}
