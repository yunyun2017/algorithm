package GraphAndDp;

public class KnapsackProblem {

    /**
     * 0-1背包：空间未优化版
     *
     * @param N 物品数量
     * @param C 背包容量
     * @param w 每个物品对应的重量数组
     * @param v 每个物品对应的价值数组
     */
    public static int knap_v1(int N, int C, int w[], int v[]) {
        int[][] f = new int[N + 1][C + 1];

        //当选取物品数为0时，它对应各个背包容量的价值都为0
        for (int i = 0; i <= C; i++) {
            f[0][i] = 0;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                if (w[i] > j) {
                    f[i][j] = f[i - 1][j];
                } else {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - w[i]] + v[i]);
                }
            }
        }

        return f[N][C];
    }

    //0-1背包：空间优化版
    public static int knap_v2(int N, int C, int w[], int v[]) {
        int[] f = new int[C + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = C; j >= w[i]; j--) {
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }

        return f[C];
    }

    //完全背包问题-未优化版
    public static int knapComplete(int N, int C, int w[], int v[]) {
        int[] f = new int[C + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                for (int k = 0; k <= j / w[i]; k++) {
                    f[j] = Math.max(f[j - k * w[i]] + k * v[i], f[j]);
                }
            }
        }

        return f[C];
    }

    //完全背包问题-优化版
    public static int knapComplete_v2(int N, int C, int w[], int v[]) {
        int[] f = new int[C + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = w[i]; j <= C; j++) {
                f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
            }
        }

        return f[C];
    }

    //多重背包问题-未优化版
    public static int knapMultible_v1(int N, int C, int w[], int v[], int num[]) {
        int[] f = new int[C + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= C; j++) {
                for (int k = 0; k <= num[i] && k <= j / w[i]; k++) {
                    f[j] = Math.max(f[j - k * w[i]] + k * v[i], f[j]);
                }
            }
        }

        return f[C];
    }

    //多重背包问题-二进制优化版
    public static int knapMultible_v2(int N, int C, int w[], int v[], int num[]) {
        int[] f = new int[C + 1];

        for (int i = 0; i < N; i++) {
            //判断是否可以按完全背包问题处理
            if (w[i] * num[i] >= C) {
                //按完全背包问题处理
                for (int j = w[i]; j <= C; j++) {
                    f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
                }
            } else {
                //0-1背包问题，进行二进制优化
                int tmpNum = 1; //num[i]进行二进制分解的数：1，2，4...
                while (tmpNum <= num[i]) {
                    for (int j = C; j >= tmpNum * w[i]; j--) {//j表示当前的背包容量
                        f[j] = Math.max(f[j], f[j - tmpNum * w[i]] + tmpNum * v[i]);
                    }
                    num[i] -= tmpNum;
                    tmpNum = tmpNum << 1;
                }

                //0-1背包的处理方式
                for (int j = C; j >= w[i]; j--) {//j表示当前的背包容量
                    f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
                }
            }
        }

        return f[C];
    }


    public static void main(String[] args) {
        int[] w = {0, 3, 4, 5};
        int[] v = {0, 4, 5, 6};
//        int[] w2 = {0, 5, 2, 6, 5, 3};
        int[] w2 = {5, 2, 6, 5, 3};
//        int[] v2 = {0, 6, 3, 5, 4, 6};
        int[] v2 = {6, 3, 5, 4, 6};
//        int[] num = {0, 1, 1, 1, 1, 3};
        int[] num = {1, 1, 1, 1, 3};
        int maxVal = knap_v1(3, 10, w, v);
        int maxVal2 = knap_v2(3, 10, w, v);
        int maxVal3 = knapComplete(3, 10, w, v);
        int maxVal4 = knapComplete_v2(3, 10, w, v);
//        int maxVal5 = knapMultible_v1(5, 10, w2, v2, num);
        int maxVal5 = knapMultible_v2(5, 10, w2, v2, num);
        System.out.println(maxVal);
        System.out.println(maxVal2);
        System.out.println(maxVal3);
        System.out.println(maxVal4);
        System.out.println(maxVal5);
    }
}
