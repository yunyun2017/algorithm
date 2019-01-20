package 剑指offer.no21_30;

/**
 * 思路：
 * 后序序列的最后一个元素是树的根结点，左子树的结点都小于根结点，右子树的结点都大于根结点
 * 然后再判断左子树序列和右子树序列是否为BST
 */
public class VerifySquenceOfBST_23 {
    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence.length == 0) return false;

        return isBst(sequence, 0, sequence.length - 1);
    }

    public boolean isBst(int[] seq, int start, int end) {
        int root = seq[end];

        int i = start;
        //找左子树序列
        for (; i < end; i++) {
            if (seq[i] > root)
                break;
        }
        //判断右子树序列中是否存在某个元素小于根结点
        for (int j = i; j < end; j++) {
            if (seq[j] < root)
                return false;
        }

        // 判断左子树序列是否为BST
        boolean left = true;
        if (i > start)
            left = isBst(seq, start, i - 1);

        //判断右子序列是否为BST
        boolean right = true;
        if (i < end)
            right = isBst(seq, i, end - 1);

        return (left && right);
    }
}
