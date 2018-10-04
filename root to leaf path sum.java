// dfs
class Solution {
    int res = 0;
    public int allPathSum(TreeNode root, int k) {
        helper(root, 0);
        System.out.println(res);
        return res;
    }
    
    private void helper(TreeNode root, int sum)
    {
        if(root == null)
            return;
        
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null)
        {
            
            res += sum;        
            return;
        }        
        helper(root.left, sum);
        helper(root.right, sum);
    }
}

// dfs + backtracking
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
    int ans = 0;
    int sum = 0;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        //sum = root.val;
        dfs(root);
        return ans;
    }

    public void dfs(TreeNode root) {
        // 1. 終止條件
        if (root == null) {
            return;
        }
        
        if (root.left == null && root.right == null) {
            sum += root.val;
            ans += sum;
            return;
        }
        
        sum += root.val;
        if (root.left != null) {
            sum *= 10;
            dfs(root.left);
            sum -= root.left.val;
            sum /= 10;
        }
        
        if (root.right != null) {
            sum *= 10;
            dfs(root.right);
            sum -= root.right.val;
            sum /= 10;
        }
    }
}