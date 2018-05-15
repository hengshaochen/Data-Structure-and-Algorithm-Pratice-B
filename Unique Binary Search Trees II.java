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
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    public List<TreeNode> helper(int min, int max) {
        List<TreeNode> ans = new ArrayList<>();
        if (min > max) {
            return ans;
        }
        for (int root = min; root <= max; root++) {
            List<TreeNode> leftTreeNodeList = helper(min, root - 1);
            List<TreeNode> rightTreeNodeList = helper(root + 1, max);
            
            if (leftTreeNodeList.size() == 0 && rightTreeNodeList.size() == 0) {
                TreeNode r = new TreeNode(root);
                ans.add(r);
            } else if (leftTreeNodeList.size() == 0) {
                // 左邊list為空，建構右邊
                for (TreeNode rightRoot : rightTreeNodeList)  {
                    TreeNode r = new TreeNode(root);
                    r.right = rightRoot;
                    ans.add(r);
                }
            } else if (rightTreeNodeList.size() == 0) {
                // 右邊list為空，建構左邊
                for (TreeNode leftRoot : leftTreeNodeList) {
                    TreeNode r = new TreeNode(root);
                    r.left = leftRoot;
                    ans.add(r);
                }
            } else {
                // 左右都不為空，建構左右的所有BST
                for (TreeNode left : leftTreeNodeList) {
                    for (TreeNode right : rightTreeNodeList) {
                        TreeNode r = new TreeNode(root);
                        r.left = left;
                        r.right = right;
                        ans.add(r);
                    }
                }
            }
        }
        return ans;
    }
}