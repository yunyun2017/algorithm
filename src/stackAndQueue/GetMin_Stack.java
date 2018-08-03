package stackAndQueue;

import java.util.Stack;

/**
 * 题目：实现一个特殊的栈， 在实现栈的基本功能的基础上， 再实现返回栈中最小元素的操作。
 * 要求：
 * 1. pop、 push、 getMin操作的时间复杂度都是O(1)
 * 2. 设计的栈类型可以使用现成的栈结构
 * 思路：
 * 设计两个栈，一个data栈用于存放用户要存放的数据，另一个min栈存放栈中最小元素，这样就能实现getMin是O(1)了
 * 步骤：
 * 每次往data栈压入数据后，将该数据和min栈的栈顶比较，如果比min栈的栈顶小，则该元素压入min栈，否则min栈不变
 */
public class GetMin_Stack {
    private Stack<Integer> data;
    private Stack<Integer> min;

    public GetMin_Stack() {
        this.data = new Stack<Integer>();
        this.min = new Stack<Integer>();
    }

    /**
     * 将num压入data栈中，同时注意压入min栈的数据是data栈中的最小数据
     *
     * @param num 待入栈元素
     */
    public void push(int num) {
        this.data.push(num);

        if (this.min.isEmpty()) {
            this.min.push(num);
        } else if (num <= this.min.peek()) {
            this.min.push(num);
        }
    }

    /**
     * 弹出data栈顶元素
     * @return 栈顶元素
     */
    public int pop(){
        if(this.data.isEmpty()){
            throw new RuntimeException("the stack is empty!");
        }
        if(this.data.peek() == this.min.peek()){
            this.min.pop();
        }

        return this.data.pop();
    }

    public int getMin(){
        if(this.min.isEmpty()){
            throw new RuntimeException("the stack is empty!");
        }

        return this.min.peek();
    }

    public static void main(String[] args) {
        GetMin_Stack stack1 = new GetMin_Stack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());
    }

}
