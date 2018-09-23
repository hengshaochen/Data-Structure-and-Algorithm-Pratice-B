// Correct
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }

        // 找下一個應該要接的點
        TreeLinkNode nextNode = root.next;
        while (nextNode != null) {
            if (nextNode.left != null) {
                nextNode = nextNode.left;
                break;
            } else if (nextNode.right != null) {
                nextNode = nextNode.right;
                break;
            }
            nextNode = nextNode.next;
        }

        // 接上去, 右邊優先, 在接左邊
        if (root.right != null) {
            root.right.next = nextNode;
            // 如果右邊接上了，左邊要接上右邊，而不是nextNode
            if (root.left != null) {
                root.left.next = root.right;
            }
        } else if (root.left != null) {
                // 如果右邊沒接上，把左邊接上nextNode
                root.left.next = nextNode;
        }
        // 先走右邊在走左邊
        connect(root.right);
        connect(root.left);

    }
}