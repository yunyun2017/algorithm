package practice03;

import java.util.Stack;

/**
 * 题目：用数组结构实现大小固定的栈
 * 思路：栈有push,pop和peek方法，设置两个变量，一个是index（始终指向新进来的数要放的位置）一个是size（这个栈的大小）
 * 步骤：
 * 1. 首先初始化一个固定大小的数组，用来充当栈
 * 2. 实现栈的push，pop，peek方法，实现进栈，出栈和取得栈顶元素三个功能
 */
public class Array_To_Stack {
    private int index;
    private int[] stack;

    //初始化一个固定大小的数组来充当栈
    public Array_To_Stack(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("The array length must more than 0");
        }

        stack = new int[initSize];
        index = 0;
    }

    /**
     * 实现进栈方法 push
     * @param num 要压栈的元素
     */
    public void push(int num){
        if(index == stack.length){  //说明栈满了
            throw new ArrayIndexOutOfBoundsException("the stack is full!");
        }

        stack[index++] =num;
    }

    /**
     * 实现出栈方法 pop
     */
    public int pop(){
        if(index == 0){//说明栈空了
            throw new ArrayIndexOutOfBoundsException("the stack is empty!");
        }

        return stack[--index];
    }

    /**
     * 实现取栈顶元素，但是不弹出该元素
     */
    public int peek(){
        if(index == 0){//说明栈空了
            throw new ArrayIndexOutOfBoundsException("the stack is empty!");
        }

        return stack[index-1];
    }

    public static void main(String[] args) {

    }
}
