// BUG
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
    public int rob(TreeNode root) {
        // base case
        if (root == null) {
            return 0;
        }
        
        int left = rob(root.left);
        int right = rob(root.right);
        
        return Math.max(root.val, left + right);
    }
}

// DFS窮舉所有排列組合 + Memorization O(N) --> 在很大的case有bug, 目前還沒解決問題，看底下的答案
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
    HashMap<TreeNode, Integer> select = new HashMap<>();
    HashMap<TreeNode, Integer> notSelect = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int ans = Math.max(root.val + helper(root.left, false) + helper(root.right, false),
                           Math.max(helper(root.left, true) + helper(root.right, true), 
                           Math.max(helper(root.left, true) + helper(root.right, false),
                                    helper(root.left, false) + helper(root.right, true))));
        return ans;
    }
    
    public int helper(TreeNode root, boolean s) {
        if (root == null) {
            return 0;
        }
        if (s == true) {
            if (select.containsKey(root)) {
                return select.get(root);
            }
        } else {
            if (notSelect.containsKey(root)) {
                return notSelect.get(root);
            }
        }
        
        int ans = 0;
        if (s == true) {
            ans = root.val + helper(root.left, false) + helper(root.right, false);
            select.put(root, ans);
        } else {
            ans = Math.max(helper(root.left, true) + helper(root.right, true), 
                           Math.max(helper(root.left, true) + helper(root.right, false),
                                    helper(root.left, false) + helper(root.right, true)));
            notSelect.put(root, ans);
        }
        
        return ans;
    }
}

// 答案：
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
    public int rob(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int val = 0;

        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }

        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }

        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
    }
}