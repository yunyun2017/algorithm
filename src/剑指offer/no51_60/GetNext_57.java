package 剑指offer.no51_60;

class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}

/*
思路：
    对于给定的结点分为两个类型：有右孩子的结点和没有右孩子的结点，下面对这两种类型结点，
    分别分析它的中序遍历的下一个结点为：
    （1）有右孩子的结点：右子树的最左结点为题目所求结点
    （2）无右孩子的结点：从该结点往上找，直到找到一个结点，使得题目所给结点在这个结点的左子树上，
         则该结点为题目所求结点
*/

public class GetNext_57 {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;

        TreeLinkNode nextNode = null;
        //有右孩子的结点
        if (pNode.right != null) {
            nextNode = pNode.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
        } else {//无右孩子
            TreeLinkNode childNode = pNode;
            TreeLinkNode parentNode = pNode.next;
            while (parentNode != null) {
                if (parentNode.left == childNode) {
                    nextNode = parentNode;
                    break;
                } else {
                    childNode = parentNode;
                    parentNode = parentNode.next;
                }
            }
        }

        return nextNode;
    }
}
