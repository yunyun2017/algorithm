package 剑指offer.no1_10;

public class Fibonacci_07 {
    public int Fibonacci(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        // return Fibonacci(n-1)+Fibonacci(n-2);
        int p1 = 0,p2 = 1;
        int i = 2;
        while(i <= n){
            p2 = p1+p2;
            p1 = p2-p1;
            i++;
        }

        return p2;
    }
}
