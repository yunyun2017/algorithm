package practice04;

/**
 * 题目：给定一个整型正方形矩阵matrix， 请把该矩阵调整成顺时针旋转90度的样子。
 * 要求：额外空间复杂度为O(1)
 * <p>
 * 思路：跟转圈打印矩阵一个思路，从宏观上面去分析这个问题。从外圈往内圈一圈一圈的旋转，当所有圈都旋转了，该矩阵就旋转完成
 * 比如有1-9这九个数的矩阵，旋转之后，1-3,3-9,9-7，7-1的位置上，2-6,6-8,8-4,4-2的位置上
 * 只要将他们旋转之后的位置和旋转之前的位置对应上，这个问题就解决了。
 * <p>
 * 注意：这里的矩阵一定是正方形矩阵，否则无法完成题意的要求
 */
public class RotateMatrix {

    /**
     * 将矩阵的某一圈的四条边调整好
     *
     * @param arr 矩阵
     * @param a   左上角横坐标
     * @param b   左上角纵坐标
     * @param c   右上角横坐标
     * @param d   右上角纵坐标
     */
    public static void rotateEdge(int[][] arr, int a, int b, int c, int d) {
        int temp;

        for (int i = 0; i < d - b; i++) {
            temp = arr[a][b + i];
            arr[a][b + i] = arr[c - i][b];
            arr[c - i][b] = arr[c][d - i];
            arr[c][d - i] = arr[a + i][d];
            arr[a + i][d] = temp;
        }
    }

    /**
     * 对矩阵arr进行顺时针90度旋转
     *
     * @param arr 要旋转的矩阵
     */
    public static void rotateMatrix(int[][] arr) {
        int a = 0, b = 0;
        int c = arr.length - 1, d = arr.length - 1;

        while (a <= c) {
            rotateEdge(arr, a++, b++, c--, d--);
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
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12},
                {13, 14, 15, 16}};
        printMatrix(matrix);
        System.out.println("============");
        rotateMatrix(matrix);
        printMatrix(matrix);
    }
}
