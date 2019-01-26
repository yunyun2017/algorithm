package 剑指offer.no11_20;

import 剑指offer.commonWidgets.TreeNode;

public class HasSubtree_17 {
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) return false;

        boolean result = false;
        if (root1.val == root2.val) {
            //判断root1是否包含root2这个子树
            result = Tree1HasTree2(root1, root2);
        }
        if (!result)
            result = HasSubtree(root1.left, root2);
        if (!result)
            result = HasSubtree(root1.right, root2);

        return result;
    }

    public boolean Tree1HasTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) return true;
        if (root1 == null) return false;
        if (root1.val != root2.val)
            return false;
        else {
            return Tree1HasTree2(root1.left, root2.left) && Tree1HasTree2(root1.right, root2.right);
        }
    }
}
