package 剑指offer.no21_30;

/**
 * 思路：
 * 1、确定如何转换：由于转换的链表是排好序的，所以考虑用中序遍历
 * 2、确定遍历过程：当遍历到根结点时，把树看成三部分：根结点，左子树和右子树。根结点将和左子树的最大结点连接起来，将和
 * 右子树的最小结点连接起来；
 * 3、当我们遍历到根结点时，左子树已经转换成一个排序链表了，并且链表中的最后一个结点是当前值最大的结点。我们把该点和根结点
 * 连接起来，此时链表中的最后一个结点就是根节点了。
 * 4、我们再去遍历右子树，然后把根结点和右子树中最小结点连接起来；
 * 5、遍历和转换左右子树的过程是一样的，自然考虑到用递归实现。
 */
class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}

public class Convert_26 {
    TreeNode lastNode = null;
    TreeNode leftNode = null;

    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) return null;

        Convert(pRootOfTree.left);
        pRootOfTree.left = lastNode;
        if (lastNode != null) lastNode.right = pRootOfTree;
        lastNode = pRootOfTree;

        leftNode = leftNode == null ? pRootOfTree : leftNode;
        Convert(pRootOfTree.right);

        return leftNode;
    }

   /* public TreeNode ConvertToLinkedlist(TreeNode root, TreeNode lastNode) {
        if (root == null) return null;

        TreeNode currentNode = root;
        lastNode = ConvertToLinkedlist(currentNode.left, lastNode);
        currentNode.left = lastNode;
        if (lastNode != null) {
            lastNode.right = currentNode;
        }
        lastNode = currentNode;

        lastNode = ConvertToLinkedlist(currentNode.right, lastNode);
        return lastNode;
    }*/

    public void inOrder1(TreeNode root) {
        if (root == null) {
            return;
        }

        inOrder1(root.left);
        System.out.print(root.val + " ");
        inOrder1(root.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(6);
        root.right = new TreeNode(14);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(8);

        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(16);

        Convert_26 cc = new Convert_26();
        cc.inOrder1(root);
        System.out.println();
        TreeNode head = cc.Convert(root);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.right;
        }
    }
}
