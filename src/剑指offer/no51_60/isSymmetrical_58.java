package 剑指offer.no51_60;

import 剑指offer.commonWidgets.TreeNode;

/*
    思路：树的遍历有3种方式：前序，中序和后序。但是这三种都是先遍历左，后遍历右。我们可以考虑定义一种遍历方式，
    这种方式先遍历右，后遍历左。
    又：结合题意，要求判断二叉树是否为对称二叉树，那么我们可以知道，对称二叉树的根左右遍历序列和根右左遍历序列
    是一样的。
    因此：我们需要定义一种和前序遍历对称的遍历序列，即根右左的遍历序列，如果该遍历序列和前序序列一致的话，说明
    该二叉树是对称的。
 */
public class isSymmetrical_58 {
    boolean isSymmetrical(TreeNode pRoot) {
        return isSymmetrical(pRoot, pRoot);
    }

    boolean isSymmetrical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        if (root1.val != root2.val)
            return false;

        return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
    }
}
