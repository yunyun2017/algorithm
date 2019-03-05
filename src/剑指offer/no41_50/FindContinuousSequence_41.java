package 剑指offer.no41_50;

import 剑指offer.no31_40.FindNumsAppearOnce_40;

import java.io.PushbackInputStream;
import java.util.ArrayList;

/*
    思路：当给定一个递增排序的数组，和一个整数s，求在这个数组中查找两个数，使得它们的和为s。
    解决这个问题的思路：数组头和尾分别设置一个指针，然后根据这两个指针指向的数字和是大于，小于还是等于s来分别移动
    头指针和尾指针。当大于时，尾指针向前移动；当小于时，头指针向后移动；当等于时，则找到这两个数字。

    本题也按照上面的思路，设置两个指针small和big分别指向序列的最小值和最大值。初始设置：small指向1，big指向2.
    若从small到big的序列和大于s，我们可以去掉序列中的最小值，就是增大small的值；
    反之，我们往序列中添加值，增大big的值。
 */
public class FindContinuousSequence_41 {
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> allSequence = new ArrayList<ArrayList<Integer>>();

        if (sum < 3)
            return allSequence;

        int small = 1, big = 2;
        int seqSum = small + big;

        while (small < big && small < (1 + sum) / 2) {
            if (seqSum < sum) {
                big++;
                seqSum += big;
            } else if (seqSum > sum) {
                seqSum -= small;
                small++;
            } else {
                ArrayList<Integer> oneSeq = FindOneSequence(small, big);
                allSequence.add(oneSeq);
                big++;
                seqSum += big;
            }
        }

        return allSequence;

    }

    public ArrayList<Integer> FindOneSequence(int small, int big) {
        ArrayList<Integer> oneSeq = new ArrayList<Integer>();

        for (int i = small; i <= big; i++) {
            oneSeq.add(i);
        }

        return oneSeq;
    }

    public void printArraylist(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindContinuousSequence_41 seq = new FindContinuousSequence_41();
        ArrayList<ArrayList<Integer>> seqs = new ArrayList<>();
        seqs = seq.FindContinuousSequence(15);

        for (int i = 0; i < seqs.size(); i++) {
            seq.printArraylist(seqs.get(i));
        }
    }
}
