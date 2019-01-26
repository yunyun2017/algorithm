package 剑指offer.no21_30;

import 剑指offer.commonWidgets.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的层次遍历，利用辅助队列来完成
 */
public class PrintFromTopToBottom_22 {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        if(root != null)
            queue.add(root);

        while (!queue.isEmpty()){
            TreeNode tmp = queue.remove();
            list.add(tmp.val);
            if(tmp.left != null) queue.add(tmp.left);
            if(tmp.right != null) queue.add(tmp.right);
        }

        return list;
    }
}
