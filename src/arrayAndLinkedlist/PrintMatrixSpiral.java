package arrayAndLinkedlist;

/**
 * 题目：
 * 给定一个整型矩阵matrix， 请按照转圈的方式打印它。
 * 例如： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
 * 打印结果为： 1， 2， 3， 4， 8， 12， 16， 15， 14， 13， 9，5， 6， 7， 11， 10
 * 要求：额外空间复杂度为O(1)
 * <p>
 * 思路：
 * 不能按照常规的想法去想，要从宏观上面去分析。
 * 当我们知道左上角(a,b)和右下角(c,d)后，我们就可以确定一个矩形，然后对这个矩形遍历一圈。
 * 这样从外圈开始一直往内圈遍历，直到把每一圈都遍历了，即：整个矩阵转圈遍历了一遍。满足题意。
 */
public class PrintMatrixSpiral {

    /**
     * 顺时针方向打印一个矩形
     *
     * @param arr 存放数据的二维数组
     * @param a   左上角点横坐标
     * @param b   左上角点纵坐标
     * @param c   右下角点横坐标
     * @param d   右下角点纵坐标
     */
    public static void printEdge(int[][] arr, int a, int b, int c, int d) {
        if (a == c) {//这些点在一条横线上
            for (int i = b; i <= d; i++) {
                System.out.print(arr[a][i] + " ");
            }
        } else if (b == d) {//这些点在一条竖线上
            for (int i = a; i <= c; i++) {
                System.out.print(arr[i][b] + " ");
            }
        } else {
            int curR = a;
            int curC = b;

            while (curC < d) {
                System.out.print(arr[a][curC++] + " ");
            }//curC =d
            while (curR < c) {
                System.out.print(arr[curR++][d] + " ");
            }//curR = c
            while (curC > b) {
                System.out.print(arr[c][curC--] + " ");
            }//curR = b
            while (curR > a) {
                System.out.print(arr[curR--][b] + " ");
            }//curR = a
        }
    }

    /**
     * 传进去一个矩阵，然后转圈打印出来
     *
     * @param arr 待操作的矩阵
     */
    public static void spiralPrintMatrix(int[][] arr) {
        //获取左上和右下角的坐标
        int a = 0, b = 0;
        int c = arr.length - 1;
        int d = arr[0].length - 1;

        while (a <= c && b <= d) {
            printEdge(arr, a++, b++, c--, d--);
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};

        spiralPrintMatrix(matrix);
    }

}
