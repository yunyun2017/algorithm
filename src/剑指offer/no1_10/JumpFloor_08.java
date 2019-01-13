package 剑指offer.no1_10;

public class JumpFloor_08 {
    public int JumpFloor(int target) {
        if(target == 1) return 1;
        if(target == 2) return 2;

        return JumpFloor(target-1)+JumpFloor(target-2);
    }
}
