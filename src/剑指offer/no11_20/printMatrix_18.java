package 剑指offer.no11_20;

import java.util.ArrayList;

/**
 * 思路：将这个矩形看成是一个一个的小矩形，题目要求就是从外圈向内圈打印一个个矩形
 * 打印矩形的思路：已知矩形的左上角和右下角坐标，可以确定一个矩形，从而可以打印出这个矩形各条边上的数字
 */
public class printMatrix_18 {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        //获取这个数组的左上角和右下角的坐标，从而确定最外圈的矩形
        int a = 0, b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length - 1;

        ArrayList<Integer> list = new ArrayList<Integer>();
        while (a <= c && b <= d) {
            printRectangle(matrix, a++, b++, c--, d--, list);
        }

        return list;
    }

    /**
     * 打印一个左上角为(a,b)，右下角为(c,d)的矩形
     *
     * @param matrix 矩形的数据源
     * @param a      左上角横坐标
     * @param b      左上角纵坐标
     * @param c      右下角横坐标
     * @param d      右下角纵坐标
     * @param list   存储要打印的矩形元素
     */
    public void printRectangle(int[][] matrix, int a, int b, int c, int d, ArrayList<Integer> list) {
        //矩形可能是一条横线，一条竖线，或者一个矩形
        if (a == c) {//y一条横线
            for (int i = b; i <= d; i++) {
                list.add(matrix[a][i]);
            }
        } else if (b == d) {//一条竖线
            for (int i = a; i <= c; i++) {
                list.add(matrix[i][b]);
            }
        } else {//矩形
            int curR = a;
            int curC = b;

            while (curC < d) {
                list.add(matrix[a][curC++]);
            }//curC=d
            while (curR < c) {
                list.add(matrix[curR++][d]);
            }//curR=c
            while (curC > b) {
                list.add(matrix[c][curC--]);
            }//curC=b
            while (curR > a) {
                list.add(matrix[curR--][b]);
            }//curR=a
        }
    }
}
