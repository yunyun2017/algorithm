package 剑指offer.no61_66;

import 剑指offer.commonWidgets.TreeNode;
import 剑指offer.commonWidgets.preTraverseTree;

public class SerializeAndDeserialize_61 {
    int index = -1; //记录反序列化时目前所访问的结点位置

    String Serialize(TreeNode root) {
        if (root == null)
            return "#,";

        return root.val + "," + Serialize(root.left) + Serialize(root.right);
    }

    TreeNode Deserialize(String str) {
        index++;

        TreeNode root = null;
        String[] arr = str.split(",");
        if (!arr[index].equals("#")) {
            root = new TreeNode(Integer.valueOf(arr[index]));
            root.left = Deserialize(str);
            root.right = Deserialize(str);
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode p = new TreeNode(8);
        p.left = new TreeNode(6);
        p.right = new TreeNode(7);
        p.left.left = new TreeNode(10);
        p.left.right = new TreeNode(5);
        p.right.left = new TreeNode(9);
        p.right.right = new TreeNode(11);

        SerializeAndDeserialize_61 seAndDe = new SerializeAndDeserialize_61();
        String str = seAndDe.Serialize(p);
        System.out.println(str);

        TreeNode node = seAndDe.Deserialize(str);
        new preTraverseTree().preOrder(node);
    }
}
