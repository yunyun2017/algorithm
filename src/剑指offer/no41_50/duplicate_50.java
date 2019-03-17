package 剑指offer.no41_50;

public class duplicate_50 {
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (length <= 0)
            return false;

        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            if (temp[numbers[i]] == 1) {
                duplication[0] = numbers[i];
                return true;
            }
            temp[numbers[i]] = 1;
        }
        return false;
    }
}
