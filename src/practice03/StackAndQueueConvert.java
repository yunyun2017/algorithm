package practice03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 用队列实现栈，用栈实现队列
 * 思路：
 * 1. 用队列实现栈：用两个队列，分别为data和help，把data队列中最后一个元素之前的所有元素全部进help，最后一个元素出队列，
 * 然后以此类推，这样两边进行捯饬，即可以实现用队列实现栈结构
 * <p>
 * 2. 用栈实现队列：用到两个栈，分别为dataPush和dataPop。数据始终进dataPush的栈中，弹出数据始终从dataPop这个栈往外弹。
 * 从dataPush往dataPop倒数据有两个条件：1）dataPush必须一次全部倒完；2）dataPop中有数据时不能往里面倒数据
 *
 */
public class StackAndQueueConvert {
    /**
     * 用队列实现栈结构
     */
    public static class QueueToStack {
        private Queue<Integer> data;
        private Queue<Integer> help;

        public QueueToStack() {
            this.data = new LinkedList<Integer>();
            this.help = new LinkedList<Integer>();
        }

        public void push(int num) {
            this.data.add(num);
        }

        public int pop() {
            if (this.data.isEmpty()) {
                throw new RuntimeException("the stack is empty!");
            }

            while (this.data.size() > 1) {
                this.help.add(this.data.poll());
            }

            int temp = this.data.poll();
            swap(data, help);
            return temp;
        }

        public int peek(){
            if (this.data.isEmpty()) {
                throw new RuntimeException("the stack is empty!");
            }

            while (this.data.size() > 1) {
                this.help.add(this.data.poll());
            }

            int temp = this.data.poll();
            this.help.add(temp);
            swap(data, help);

            return temp;
        }

        public void swap(Queue<Integer> data, Queue<Integer> help) {
            Queue<Integer> temp = data;
            data = help;
            help = temp;
        }


    }

    /**
     * 用栈实现队列
     */
    public static class StackToQueue{
        private Stack<Integer> dataPush;
        private Stack<Integer> dataPop;

        public StackToQueue() {
            dataPush = new Stack<Integer>();
            dataPop = new Stack<Integer>();
        }

        public void push(int num){
            dataPush.push(num);
        }

        public int poll(){
            if(dataPop.isEmpty() && dataPush.isEmpty()){
                throw new RuntimeException("the queue is empty!");
            }else if(dataPop.isEmpty()){
                //将dataPush中的数据一次性全部倒入dataPop中
                while (!dataPush.isEmpty()){
                    dataPop.push(dataPush.pop());
                }
            }

            return dataPop.pop();
        }

        public int peek(){
            if(dataPop.isEmpty() && dataPush.isEmpty()){
                throw new RuntimeException("the queue is empty!");
            }else if(dataPop.isEmpty()){
                //将dataPush中的数据一次性全部倒入dataPop中
                while (!dataPush.isEmpty()){
                    dataPop.push(dataPush.pop());
                }
            }

            return dataPop.peek();

        }

    }

    public static void main(String[] args) {
        QueueToStack qs = new QueueToStack();
        qs.push(1);
        qs.push(2);
        System.out.println(qs.peek());
        qs.push(5);
        System.out.println(qs.pop());

        System.out.println("===========================");

        StackToQueue sq = new StackToQueue();
        sq.push(5);
        sq.push(6);
        sq.push(7);
        System.out.println(sq.peek());
        System.out.println(sq.poll());
        sq.push(8);
        System.out.println(sq.peek());
    }
}
