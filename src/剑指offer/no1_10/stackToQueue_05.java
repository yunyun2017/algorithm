package 剑指offer.no1_10;

import java.util.Stack;

public class stackToQueue_05 {
    /**
     * 两个栈里面的数字进行相互倾倒，其中：
     一个栈（例如：stack1）只接受push进来的数字，另一个栈（例如：stack2）只接受pop出去的数字
     */
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while(!stack2.isEmpty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
