package 剑指offer.no51_60;

/*
    思路：可以把B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]看成是A[0]*A[1]*...*A[i-1]和A[i+1]*...*A[n-1]
    两部分的乘积。因此，数据B可以用一个矩形来表示
    B0：  1  A1  A2...An-2  An-1
    B1：  A0  1  A2...An-2  An-1
    B2:   A0 A1   1...An-2  An-1
    ...   A0 A1  ...1 An-2  An-1
    BN-2: A0 A1  ...An-3 1  An-1
    BN-1: A0 A1  ...An-3 An-2  1

    我们可以定义C[i]=A[0]*A[1]*...*A[i-1],D[i]=A[i+1]*...*A[n-2]*A[n-1]
    C[i]可以用自上而下的顺序算出来,C[i]=C[i-1]*A[i-1]，
    D[i]可以用自下而上的顺序算出来,D[i]=D[i+1]*A[i+1]
 */
public class multiply_51 {
    public int[] multiply(int[] A) {
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        int[] D = new int[A.length];

        if (A.length == 0) return B;

        C[0] = 1;
        for (int i = 1; i < C.length; i++) {
            C[i] = C[i - 1] * A[i - 1];
        }

        D[A.length - 1] = 1;
        B[A.length - 1] = C[A.length - 1] * D[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            D[i] = D[i + 1] * A[i + 1];
            B[i] = C[i] * D[i];
        }

        return B;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5};
        multiply_51 mul = new multiply_51();
        int[] B = mul.multiply(A);
        for (int i = 0; i < B.length; i++)
            System.out.print(B[i] + " ");
    }
}
