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
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        
        // 從map找most freq
        List<Integer> ans = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (Integer cur : map.keySet()) {
            if (map.get(cur) > max) {
                ans.clear();
                ans.add(cur);
                max = map.get(cur);
            } else if (map.get(cur) == max) {
                ans.add(cur);
            }
        }
        
        int[] ansArray = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        return ansArray;
    }
    
    public int dfs(TreeNode root, Map<Integer, Integer> map) {
        // base case
        if (root == null) {
            return 0;
        }
        
        // 傳給兒子（拆分）
        int leftSubtreeSum = dfs(root.left, map);
        int rightSubtreeSum = dfs(root.right, map);
        
        // 這層要做的事
        int sum = leftSubtreeSum + rightSubtreeSum + root.val;
        if (!map.containsKey(sum)) {
            // 如果這個subtreeSum第一次出現
            map.put(sum, 1);
        } else {
            // 如果出現過
            map.put(sum, map.get(sum) + 1);
        }
        
        return sum;
    }
}

// 小優化，在dfs中順便紀錄max_count方便做完dfs後掃map找most freq, 這樣就不用ans.clear這個O(n)的方法了
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
    int maxCount = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        dfs(root, map);
        
        // 從map找most freq
        List<Integer> ans = new ArrayList<>();
        for (Integer cur : map.keySet()) {
            if (map.get(cur) == maxCount) {
                ans.add(cur);
            }
        }
        
        int[] ansArray = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            ansArray[i] = ans.get(i);
        }
        return ansArray;
    }
    
    public int dfs(TreeNode root, Map<Integer, Integer> map) {
        // base case
        if (root == null) {
            return 0;
        }
        
        // 傳給兒子（拆分）
        int leftSubtreeSum = dfs(root.left, map);
        int rightSubtreeSum = dfs(root.right, map);
        
        // 這層要做的事
        int sum = leftSubtreeSum + rightSubtreeSum + root.val;
        if (!map.containsKey(sum)) {
            // 如果這個subtreeSum第一次出現
            map.put(sum, 1);
        } else {
            // 如果出現過
            map.put(sum, map.get(sum) + 1);
        }
        
        maxCount = Math.max(maxCount, map.get(sum));
        
        return sum;
    }
}