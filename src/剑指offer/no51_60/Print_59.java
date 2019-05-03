package 剑指offer.no51_60;

import 剑指offer.commonWidgets.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/*
思路：用到两个栈，栈1存储的元素都是二叉树奇数行从右到左入栈的元素，栈2存储的元素都是二叉树偶数行从左到右入栈的元素，
然后两个栈轮流交替做出栈操作，即可实现元素的之字形打印
 */
public class Print_59 {
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> seq = new ArrayList<>();

        if (pRoot == null)
            return seq;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(pRoot);
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            ArrayList<Integer> rowList1 = new ArrayList<>();
            while (!stack1.isEmpty()) {
                TreeNode topNode = stack1.pop();
                rowList1.add(topNode.val);
                if (topNode.left != null)
                    stack2.push(topNode.left);
                if (topNode.right != null)
                    stack2.push(topNode.right);
            }
            if (rowList1.size() > 0)
                seq.add(rowList1);

            ArrayList<Integer> rowList2 = new ArrayList<>();
            while (!stack2.isEmpty()) {
                TreeNode topNode = stack2.pop();
                rowList2.add(topNode.val);
                if (topNode.right != null)
                    stack1.push(topNode.right);
                if (topNode.left != null)
                    stack1.push(topNode.left);
            }
            if (rowList2.size() > 0)
                seq.add(rowList2);
        }

        return seq;
    }
}
