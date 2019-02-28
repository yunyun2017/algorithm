package 剑指offer.no31_40;

/*
      思路：平衡二叉树的条件：树中的任何一棵子树的左右子树高度差绝对值<=1
      实现一个函数，用于计算一棵树的高度。在计算树的高度过程中，如果该树的左右子树高度差的
      绝对值>1，那么这棵树就不是平衡二叉树，返回-1，后续也不用遍历剩余结点了；如果该树是一棵
      平衡二叉树，则返回该树的高度。
  */

import 剑指offer.commonWidgets.TreeNode;

import java.lang.Math;

public class IsBalanced_Solution_39 {

    public boolean IsBalanced_Solution(TreeNode root) {
        return GetTreeDepth(root) == -1 ? false : true;
    }

    public int GetTreeDepth(TreeNode root) {
        if (root == null)
            return 0;

        int leftDepth = GetTreeDepth(root.left);
        if (leftDepth == -1)
            return -1;
        int rightDepth = GetTreeDepth(root.right);
        if (rightDepth == -1)
            return -1;

        return Math.abs(leftDepth - rightDepth) > 1 ? -1 : Math.max(leftDepth, rightDepth) + 1;
    }
}
