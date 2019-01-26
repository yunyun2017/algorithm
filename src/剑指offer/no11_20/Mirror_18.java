package 剑指offer.no11_20;

import 剑指offer.commonWidgets.TreeNode;

public class Mirror_18 {
    public TreeNode Mirror(TreeNode root) {
        return getMirrorTree(root);
    }

    public TreeNode getMirrorTree(TreeNode root) {
        if (root == null) return null;

        TreeNode mirrorRoot = new TreeNode(root.val);

        mirrorRoot.left = getMirrorTree(root.right);
        mirrorRoot.right = getMirrorTree(root.left);

        return mirrorRoot;
    }

    //剑指offer上面的方法：直接修改传入的树的结构

    /**
     * 思路：我们先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点。
     * 当交换完所有非叶结点的左右子结点后，就得到了树的镜像
     * @param root
     */
    public void Mirror_2(TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) return;

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror_2(root.left);
        Mirror_2(root.right);
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
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);

        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(11);

        Mirror_18 m = new Mirror_18();
        m.preOrder1(m.Mirror(root));

    }
}
