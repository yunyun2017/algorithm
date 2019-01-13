package 剑指offer.no1_10;


/**
 * 首先想到的就是用递归来实现，递归建左右子树，然后再加上根节点，完成这棵树的建立
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class reConstructBinaryTree_04 {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0)
            return null;

        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {

        if (preEnd < preStart || inEnd < inStart) return null;

        TreeNode root = new TreeNode(pre[preStart]);
        root.left = root.right = null;
        int rootIndex;
        for (rootIndex = inStart; rootIndex <= inEnd; rootIndex++) {
            if (in[rootIndex] == pre[preStart]) break;
        }
        int leftLengh = rootIndex - inStart;


        root.left = reConstructBinaryTree(pre, preStart + 1, preStart + leftLengh, in, inStart, rootIndex - 1);
        root.right = reConstructBinaryTree(pre, preStart + leftLengh + 1, preEnd, in, rootIndex + 1, inEnd);

        return root;

    }

    public void preOrder1(TreeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.val + " ");
        preOrder1(root.left);
        preOrder1(root.right);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 3, 4, 5, 6, 7};
        int[] in = {3, 2, 4, 1, 6, 5, 7};

        reConstructBinaryTree_04 cbt = new reConstructBinaryTree_04();
        TreeNode root = cbt.reConstructBinaryTree(pre, in);

        cbt.preOrder1(root);
    }


}
