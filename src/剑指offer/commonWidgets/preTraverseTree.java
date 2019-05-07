package 剑指offer.commonWidgets;

public class preTraverseTree {
    public void preOrder(TreeNode root) {
        if (root == null)
            return;

        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
}
