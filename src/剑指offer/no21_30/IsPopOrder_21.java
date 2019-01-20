package 剑指offer.no21_30;

import java.util.Stack;

/**
 * 我的思路：一个辅助栈和两个指针，分别指向push数组和pop数组的第一个元素
 * 1、遍历push数组，每遇到一个元素，先进栈，然后循环比较栈顶元素和pop数组当前指向的元素；
 * 2、若相等，则辅助栈将栈顶元素pop，pop数组指针向后走一个，继续比较栈顶元素和pop数组当前指向的元素
 * 3、若辅助栈为空，或者栈顶元素不等于pop数组当前指向的元素，则push数组的指针向后走一个，然后进入步骤1；
 * 4、若push数组遍历完，跳出循环后，辅助栈为空，则返回true，否则返回false。
 *
 * 规律：
 * 如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。如果下一个弹出的数字不在栈顶，我们把压栈序列中还没有入栈的数字
 * 压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该
 * 序列一定不可能是一个弹出序列
 */
public class IsPopOrder_21 {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stackPush = new Stack<Integer>();
        int i = 0;

        for (int j = 0; j < pushA.length; j++) {
            stackPush.push(pushA[j]);
            while (!stackPush.isEmpty() && stackPush.peek() == popA[i]) {
                stackPush.pop();
                i++;
            }
        }

        if (!stackPush.isEmpty())
            return false;

        return true;

    }
}
