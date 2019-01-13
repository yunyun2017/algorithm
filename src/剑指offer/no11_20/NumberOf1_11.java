package 剑指offer.no11_20;

public class NumberOf1_11 {
    //处理负数有问题
    public int NumberOf1(int n) {
        if (n == 0) {
            return 0;
        }

        int count = 0;
        //处理正数
        if (n > 0) {
            while (n > 0) {
                count = n % 2 == 1 ? count + 1 : count;
                n /= 2;
            }

            return count;
        }
        //处理负数
        if (n < 0) {
            int absN = Math.abs(n);
            int i = 100;
            int[] array = new int[i];
            while (absN != 0) {
                array[i] = absN % 2;
                absN /= 2;
                i--;
            }

            for (int j = 100; j >= 0; j--) {
                if (array[j] == 1) {
                    for (int k = 0; k < j; k++) {
                        array[k] = array[k] == 0 ? 1 : 0;
                    }
                    break;
                }
            }

            for (int m = 0; m < array.length; m++) {
                count = array[m] == 1 ? count + 1 : count;
            }

            return count;

        }
        return count;
    }

    //用位运算来解决
    public int NumberOf1_1(int n) {
        //这里对flag的判断可能有点问题
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) == 1)
                count++;

            flag = flag << 1;
        }

        return count;
    }

    //位运算-最优解，一个整数的二进制中有几个1就循环几次

    /**
     * 思路：把一个整数减去1，再和原整数做位与运算，会把该整数最右边一个1变成0
     * @param n
     * @return
     */
    public int NumberOf1_2(int n){
        int count =0;
        while (n!=0){
            count++;
            n = (n-1) & n;
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOf1_11 num = new NumberOf1_11();
        System.out.println(num.NumberOf1_2(9));
//        System.out.println(num.NumberOf1(-9));
    }
}
