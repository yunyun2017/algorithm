package 剑指offer.no31_40;

import 剑指offer.commonWidgets.TreeNode;

import java.lang.Math;

/*
    思路：二叉树的深度 = max(左子树深度，右子树深度)+1
    求左右子树的深度和父问题一样，因此，可以用递归实现。
*/
public class TreeDepth_38 {
    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = TreeDepth(root.left);
        int rightDepth = TreeDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }
}
