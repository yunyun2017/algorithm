package 剑指offer.no41_50;

import java.util.LinkedList;

/*
    本题是典型的约瑟夫环问题

 */
public class LastRemaining_Solution_46 {
    //循环链表实现
    public int LastRemaining_Solution1(int n, int m) {
        if (n < 1 || m < 1)
            return -1;

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }

        int outPos = 0;//出圈的位置
        while (list.size() > 1) {
            outPos = (outPos + m - 1) % list.size();//实现循环链表的效果
            list.remove(outPos);
        }

        return list.get(0);
    }

    //数学优化法，从数学模型上找规律，然后推导出一般情况的公式
    //假设有n个人时，最后的获胜位置是P(n)，那么出圈一个人后剩n-1人，则获胜位置是P(n-1)，现在的目标就是求P(n)和P(n-1)的关系
    //n个人时：   0,   1,  2,.......k-1, k, k+1,...Pn,...n-1  k出圈
    //n-1个人时：n-k-1,n-k,n-k+1,...,n-2,   0,.....Pn-1, n-k-2
    //由此，我们可以推出：P(n)=(P(n-1)+k+1)%n，而k=m-1，所以P(n)=(P(n-1)+m)%n
    //由题意，知：P(1)=0
    public int LastRemaining_Solution2(int n, int m) {
        if (n < 1 || m < 1)
            return -1;
        if (n == 1)
            return 0;

        return (LastRemaining_Solution2(n - 1, m) + m) % n;
    }
}
