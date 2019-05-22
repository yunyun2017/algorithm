package 剑指offer.no61_66;

import 剑指offer.commonWidgets.TreeNode;

/*
    二叉搜索树的中序遍历结果就是一个递增的排序序列，所有对二叉搜索树的中序遍历算法进行改造即可满足题意
 */
public class KthNode_62 {
    int count = 0;

    TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null || k == 0)
            return null;

        TreeNode KthNode;

        if (pRoot == null)
            return null;

        KthNode = KthNode(pRoot.left, k);

        if (KthNode == null) {
            count++;
            if (count == k) {
                return pRoot;
            }
        }

        if (KthNode == null) {
            KthNode = KthNode(pRoot.right, k);
        }

        return KthNode;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(5);
        p.left = new TreeNode(3);
        p.right = new TreeNode(7);
        p.left.left = new TreeNode(2);
        p.left.right = new TreeNode(4);
        p.right.left = new TreeNode(6);
        p.right.right = new TreeNode(8);

        KthNode_62 kthnode = new KthNode_62();
        TreeNode node = kthnode.KthNode(p, 0);
        System.out.println(node.val);
    }
}
