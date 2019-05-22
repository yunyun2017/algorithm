package 剑指offer.no61_66;

import java.util.ArrayDeque;
import java.util.ArrayList;
/*
    思路：用双端队列作为滑动窗口的数据存储容器，这样可以实现O(1)时间找到滑动窗口的最大值，这样找到所有滑动窗口
    的最大值的时间为O(n)
 */

public class maxInWindows_64 {
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> maxInWindow = new ArrayList<>();

        if (num.length >= size && size > 0) {
            //处理过程
            ArrayDeque<Integer> slideWindow = new ArrayDeque<>();
            //先把滑动窗口填满
            for (int i = 0; i < size; i++) {
                while (!slideWindow.isEmpty() && num[i] > num[slideWindow.peekLast()]) {
                    slideWindow.pollLast();
                }
                slideWindow.offerLast(i);
            }
            //滑动窗口依次往后滑，依次找出滑动窗口的最大值，存入maxInWindow中
            for (int i = size; i < num.length; i++) {
                maxInWindow.add(num[slideWindow.peekFirst()]);

                //当前的最大值已经滑出窗口了
                if (i - slideWindow.peekFirst() >= size) {
                    slideWindow.pollFirst();
                }
                while (!slideWindow.isEmpty() && num[i] > num[slideWindow.peekLast()]) {
                    slideWindow.pollLast();
                }
                slideWindow.offerLast(i);
            }
            maxInWindow.add(num[slideWindow.peekFirst()]);

        }
        return maxInWindow;
    }
}
