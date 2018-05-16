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
    public int countUnivalSubtrees(TreeNode root) {
        int[] ans = new int[1];
        helper(root, ans);
        return ans[0];
    }
    
    public boolean helper(TreeNode root, int[] ans) {
        // base case
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            ans[0]++;
            return true;
        }
        
        // 1.
        boolean l = helper(root.left, ans);
        boolean r = helper(root.right, ans);
        
        // 2.
        if (l == true && r == true) {
            // 必須左右都和root.val相等，只要有一個不相等就false
            if (root.left != null && root.left.val != root.val) {
                return false;
            }
            if (root.right != null && root.right.val != root.val) {
                return false;
            }
            
            // 到這行代表通過上面兩個測試，代表左右都相等
            ans[0]++;
            return true;
        }
        // 其他情況，代表左右子樹一定有其中一個不為true，代表左右子樹不是univalue subtree，那這個root一定不是
        // 只要該root的subtree不為univalue，這顆root的樹一定不會是univalue subtree
        return false;
    }
}