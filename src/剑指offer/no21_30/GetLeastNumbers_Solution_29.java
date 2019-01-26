package 剑指offer.no21_30;

import 剑指offer.commonWidgets.QuickSortPartition;

import java.util.ArrayList;

/**
 * 常规思路：
 * 对输入的数进行排序，然后输出前k个数，此时时间复杂度为O(nlogn)
 * <p>
 * 思路一：O(n)的算法，只有当我们可以修改输入的数组时可用
 * 基于第28题的启发，我们同样可以用Partition这个函数来解决这个问题。
 * 如果基于数组的第k个数字来调整，使得比第k个数字小的所有数字都位于数组的左边；比第k个数字大的所有数字都位于数组的
 * 右边。这样调整后，位于数组中左边的k个数字就是最小的k个数字（这k个数不一定时排序的）
 * <p>
 * 思路二：O(nlogk)的算法，特别适合处理海量数据
 * 我们可以先创建一个大小为k的数据容器来存储最小的k个数字，接下来我们每次从输入的n个整数中读入一个数。
 * 如果容器中已有的数字少于k个，则直接把这次读入的数字放入容器中；如果容器中已有k个数，此时我们不能再插入新的数字，
 * 而只能替换已有的数字。找出已有的k个数中的最大值，然后拿这次待插入的整数和最大值进行比较。如果待插入的值比当前已有
 * 的最大值小，则用这个数替换当前已有的最大值；如果待插入的值比当前已有的最大值还要大，那么这个数不可能是最小的k个数
 * 之一，可以直接抛弃。
 * 因此，当容器满了之后我们要做3件事：一是在k个数中找到最大数；二是有可能在这个容器中删除最大数；三是有可能要插入一个
 * 新的数字。考虑用二叉树来实现这个数据容器，我们总可以在O(logk)时间内实现这三步。对于n个输入数字，总的时间复杂度为
 * O(nlogk)。
 * 对于数据容器：由于我们每次都需要找到k个数中的最大值，因此我们很容易考虑使用大根堆。这里我们考虑使用STL中的PriorityQueue
 * 来实现。java里面的PriorityQueue是小顶堆实现，因此我们在构建时，需要传入一个比较器，将其改为大顶堆。
 *
 *
 *
 */
public class GetLeastNumbers_Solution_29 {
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input.length == 0 || input.length < k || k == 0)
            return list;

        int start = 0;
        int end = input.length - 1;
        QuickSortPartition part = new QuickSortPartition();
        int pos = part.Partition(input, start, end);
        while (pos != k - 1) {
            if (pos > k - 1) {
                end = pos - 1;
                pos = part.Partition(input, start, end);
            } else {
                start = pos + 1;
                pos = part.Partition(input, start, end);
            }
        }

        //此时input里面0 - k-1这k个元素就是最小的k个数
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] input = {4, 5, 1, 6, 2, 7, 3, 8};
        GetLeastNumbers_Solution_29 gl = new GetLeastNumbers_Solution_29();
        ArrayList<Integer> list = gl.GetLeastNumbers_Solution(input, 4);
        for (int i = 0; i < list.size(); i++)
            System.out.print(list.get(i) + " ");
    }
}
