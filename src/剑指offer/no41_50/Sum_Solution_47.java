package 剑指offer.no41_50;

//思路：利用逻辑与的短路求值来实现递归的终止
public class Sum_Solution_47 {
    public int Sum_Solution(int n) {
        int result = n;
        boolean res = (n != 0) && ((result += Sum_Solution(n - 1)) != 0);
        return result;
    }
}
