/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) {
            return null;
        }
        
        if (root.val < L) {
            // 如果當前root小於最小值，右邊（包含root）全部捨棄，往左走
            return trimBST(root.right, L, R);
        }
        if (root.val > R) {
            // 如果當前root大於最大值，左邊（包含root）全部捨棄，往右走
            return trimBST(root.left, L, R);
        }
        // 其他
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}