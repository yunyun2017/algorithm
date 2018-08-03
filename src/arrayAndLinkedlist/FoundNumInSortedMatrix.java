package arrayAndLinkedlist;

/**
 * 题目：给定一个有N*M的整型矩阵matrix和一个整数K，matrix的每一行和每一 列都是排好序的。
 * 实现一个函数， 判断K是否在matrix中。
 * 要求：时间复杂度为O(N+M)， 额外空间复杂度为O(1)
 * <p>
 * 思路：两种思路
 * 思路一：从右上角的元素开始找，K比它小，则往左走，走到某个比K小的元素，从这个元素再往下走，一次类推，直到找到该元素，
 * 或者到达边界，结束。
 * 思路二：跟思路一一个样式，只不过从左下角开始往右上找
 */
public class FoundNumInSortedMatrix {
    /**
     * 在一个行列都排好序的数组arr中查找k
     *
     * @param arr 排好序的数组arr
     * @param k   要查找的元素
     * @return 是否找到
     */
    public static boolean findNum(int[][] arr, int k) {
        if (arr.length == 0) {
            return false;
        }

        int m = arr.length;//行
        int n = arr[0].length;//列
        int row = 0, count = n - 1;  //记录当前活动点的行列

        while (row < m && n >= 0) {
            if (arr[row][count] > k) {
                count--;
            } else if (arr[row][count] < k) {
                row++;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6},// 0
                {10, 12, 13, 15, 16, 17, 18},// 1
                {23, 24, 25, 26, 27, 28, 29},// 2
                {44, 45, 46, 47, 48, 49, 50},// 3
                {65, 66, 67, 68, 69, 70, 71},// 4
                {96, 97, 98, 99, 100, 111, 122},// 5
                {166, 176, 186, 187, 190, 195, 200},// 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int K = 233;

        System.out.println(findNum(matrix, K));
    }
}
