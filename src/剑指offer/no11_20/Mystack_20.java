package 剑指offer.no11_20;

import java.util.Stack;

/**
 * 思路：
 * 准备两个栈：数据栈和辅助栈，数据栈用于正常的push和pop，辅助栈用于：每次向数据栈push元素时同时将最小值压入辅助栈
 * 这样，辅助栈的栈顶始终都是最小元素。当最小元素从数据栈弹出，同时也弹出辅助栈的栈顶元素，
 * 此时辅助栈的新栈顶元素就是下一个最小值
 */
public class Mystack_20 {
    Stack<Integer> dataStack = new Stack<Integer>();
    Stack<Integer> helpStack = new Stack<Integer>();

    public void push(int node) {
        dataStack.push(node);
        if (helpStack.isEmpty() || node < helpStack.peek()) {
            helpStack.push(node);
        } else {
            helpStack.push(helpStack.peek());
        }
    }

    public void pop() {
        if (dataStack.isEmpty() || helpStack.isEmpty())
            throw new RuntimeException("当前栈为空！");

        dataStack.pop();
        helpStack.pop();

    }

    public int top() {
        if (dataStack.isEmpty())
            throw new RuntimeException("当前栈为空！");

        return dataStack.peek();
    }

    public int min() {
        if (helpStack.isEmpty())
            throw new RuntimeException("当前栈为空！");

        return helpStack.peek();
    }
}
