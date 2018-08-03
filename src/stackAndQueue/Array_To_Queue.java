package stackAndQueue;

/**
 * 题目：用数组结构实现大小固定的队列（先进先出，从尾进从头出）
 * 思路：队列有push,poll和peek方法，设置三个变量：start,end和size
 * start:指向的是要弹出的那个元素的位置
 * end:指向的是新进来的数要放的位置
 * size:队列中有多少元素
 * <p>
 * 步骤：
 * 1. 首先初始化一个固定大小的数组，用来充当队列
 * 2. 实现栈的push，poll，peek方法，实现进队列，出队列和取得队头元素三个功能
 */
public class Array_To_Queue {
    private int start = 0;
    private int end = 0;
    private int size = 0;
    private int[] queue;

    public Array_To_Queue(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("the initSize must more than 0!");
        }

        queue = new int[initSize];
    }

    /**
     * 数num进队列
     *
     * @param num 待操作的数组
     */
    public void push(int num) {
        if (size == queue.length) {//队列满了
            throw new ArrayIndexOutOfBoundsException("the queue is full!");
        }

        queue[end] = num;
        size++;
        end = end == queue.length - 1 ? 0 : end + 1;
    }

    /**
     * 弹出队头元素
     *
     * @return 队头元素
     */
    public int poll() {
        if (size == 0) {//队列空了
            throw new ArrayIndexOutOfBoundsException("the queue is empty!");
        }

        int temp = queue[start];
        size--;
        start = start == queue.length - 1 ? 0 : start + 1;

        return temp;
    }

    /**
     * 取队头元素返回，但是队头元素不弹出
     * @return
     */
    public int peek(){
        if (size == 0) {//队列空了
            throw new ArrayIndexOutOfBoundsException("the queue is empty!");
        }

        return queue[start];
    }

    public static void main(String[] args) {

    }
}
