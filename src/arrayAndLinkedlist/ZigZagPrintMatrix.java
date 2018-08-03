package arrayAndLinkedlist;

/**
 * 题目：给定一个矩阵matrix， 按照“之” 字形的方式打印这个矩阵，
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12“之” 字形打印的结果为： 1， 2， 5， 9， 6， 3， 4， 7， 10， 11，8， 12
 * <p>
 * 要求：额外空间复杂度为O(1)
 * <p>
 * 思路：跟前面的转圈打印数组，旋转数组的思路一样，都是宏观来进行操作
 * 1. 用到两个点A和B，A只往右走，当走到最右边时，再往下走；B只往下走，当走到最底部时再往右走。A和B一定同时到达右下角的点
 * 2. A和B每走一步，连接A和B，就会形成一条对角线，把这些对角线连起来就是我们要求的“之”字形。
 * 3. 我们在连接A和B时，要注意下：是从A连接到B，还是从B连接到A的问题
 */
public class ZigZagPrintMatrix {

    /**
     * 连接A和B之间所有点的连线，根据flag来确定是从A到B，还是从B到A
     *
     * @param arr  待操作数组
     * @param aR   A的横坐标
     * @param aC   A的纵坐标
     * @param bR   B的横坐标
     * @param bC   B的纵坐标
     * @param flag 用来控制连线的方向
     */
    public static void printPath(int[][] arr, int aR, int aC, int bR, int bC, boolean flag) {
        if (flag) {
            while (aR <= bR) {
                System.out.print(arr[aR++][aC--] + " ");
            }
        } else {
            while (bR >= aR) {
                System.out.print(arr[bR--][bC++] + " ");
            }
        }
    }

    /**
     * 之字形打印矩阵arr
     *
     * @param arr 待操作数组
     */
    public static void ZigZagPrint(int[][] arr) {
        //确定A和B这两个点和结束点坐标
        int aR = 0, aC = 0;
        int bR = 0, bC = 0;
        int endR = arr.length - 1;
        int endC = arr[0].length - 1;
        boolean flag = false;   //标识AB连线的方向，从A到B是true，从B到A是false

        while (aR <= endR) {
            printPath(arr, aR, aC, bR, bC, flag);
            //A要往右移动，B要往下移动
            aR = aC == endC ? aR + 1 : aR;
            aC = aC == endC ? aC : aC + 1;
            bC = bR == endR ? bC + 1 : bC;
            bR = bR == endR ? bR : bR + 1;
            flag = !flag;
        }

    }

    /**
     * 打印二维矩阵arr
     *
     * @param arr 矩阵
     */
    public static void printMatrix(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        ZigZagPrint(matrix);
    }
}
