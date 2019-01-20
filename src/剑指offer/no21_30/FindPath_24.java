package 剑指offer.no21_30;

import 剑指offer.TreeNode;

import java.util.ArrayList;

/**
 * 思路：
 * 1、路径的定义：从根结点出发到叶结点所经过的所有结点，形成一条路径。
 * 结论：路径的起点是根结点，终点是叶结点，所以我们需要先遍历根结点。树的三种遍历中只有先序遍历是先遍历根结点
 * <p>
 * 规律：
 * 当用前序遍历的方式访问到某一结点时，我们把该结点添加到路径上，并累加该结点的值。
 * 如果该结点为叶结点且路径中结点值之和为输入的整数，则当前路径符合题意，我们把它存到总路径中。
 * 如果当前结点不是叶结点，则继续访问它的子结点。
 * 当前结点访问结束后，递归函数将自动回到它的父结点。因此我们在函数退出之前要在路径上删除当前结点并减去当前结点
 * 的值，以保证返回父结点时路径刚好是从根结点到父结点的路径。
 */
public class FindPath_24 {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> path_all = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> path_one = new ArrayList<Integer>();
        int sum = 0;

        if (root == null) return path_all;

        findPaths(root, target, path_all, path_one, sum);

        return path_all;
    }

    //利用先序遍历来查找路径
    public void findPaths(TreeNode root, int target, ArrayList<ArrayList<Integer>> path_all,
                          ArrayList<Integer> path_one, int curSum) {
        curSum += root.val;
        path_one.add(root.val);

        boolean isLeaf = root.left == null && root.right == null;
        //该结点是叶结点，且此时路径结点值之和等于输入值
        if (isLeaf && curSum == target) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < path_one.size(); i++)
                temp.add(path_one.get(i));
            path_all.add(temp);
        }
        //该结点不是叶结点
        if (root.left != null) {
            findPaths(root.left, target, path_all, path_one, curSum);
        }
        if (root.right != null) {
            findPaths(root.right, target, path_all, path_one, curSum);
        }

        path_one.remove(path_one.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(12);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(7);

        FindPath_24 fp = new FindPath_24();
        fp.FindPath(root, 22);
    }
}
