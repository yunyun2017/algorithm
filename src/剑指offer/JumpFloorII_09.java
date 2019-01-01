package 剑指offer;

public class JumpFloorII_09 {
    public int JumpFloorII(int n) {
        if(n == 0) return 1;
        if(n==1) return 1;

        int sum = 2;
        int i = 2;
        while(i < n){
            sum += JumpFloorII(i);
            i++;
        }

        return sum;
    }
}
