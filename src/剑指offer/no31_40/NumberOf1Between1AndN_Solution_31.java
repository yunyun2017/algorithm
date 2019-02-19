package 剑指offer.no31_40;

/**
 * 常规思路：遍历这些数，对每个数做除10取余，看这个余数是否为1，从而判断这个整数的个位数是否为1
 */
public class NumberOf1Between1AndN_Solution_31 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;

        for (int i=1;i<=n;i++){
            int temp = i;
            while (temp != 0) {
                if (temp % 10 == 1)
                    count++;

                temp /= 10;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        NumberOf1Between1AndN_Solution_31 num = new NumberOf1Between1AndN_Solution_31();
        int result = num.NumberOf1Between1AndN_Solution(12);
        System.out.println(result);
    }

}
