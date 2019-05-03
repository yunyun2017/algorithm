package 剑指offer.no51_60;

import 剑指offer.commonWidgets.ListNode;
import 剑指offer.commonWidgets.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Print_60 {
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> seq = new ArrayList<>();

        if (pRoot == null)
            return seq;

        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();

        queue1.offer(pRoot);
        while (!queue1.isEmpty() || !queue2.isEmpty()) {
            ArrayList<Integer> row1 = new ArrayList<>();
            while (!queue1.isEmpty()) {
                TreeNode node = queue1.poll();
                row1.add(node.val);
                if (node.left != null)
                    queue2.offer(node.left);
                if (node.right != null)
                    queue2.offer(node.right);
            }
            if (row1.size() > 0)
                seq.add(row1);

            ArrayList<Integer> row2 = new ArrayList<>();
            while (!queue2.isEmpty()) {
                TreeNode node = queue2.poll();
                row2.add(node.val);
                if (node.left != null)
                    queue1.offer(node.left);
                if (node.right != null)
                    queue1.offer(node.right);
            }
            if (row2.size() > 0)
                seq.add(row2);
        }

        return seq;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);
        p.right.left = new TreeNode(6);
        p.right.right = new TreeNode(7);

        Print_60 print = new Print_60();
        ArrayList<ArrayList<Integer>> list = print.Print(p);

    }
}
