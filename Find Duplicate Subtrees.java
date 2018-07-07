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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // 用兩個map, 一個是存map<編碼後的String, 該subtree的root> , 另一個存map<編碼後的String, 是否有duplicate>
        List<TreeNode> ans = new ArrayList<>();
        Map<String, TreeNode> subtreeMapping = new HashMap<>();
        Map<String, Boolean> duplicate = new HashMap<>();
        if (root == null) {
            return ans;
        }
        
        postOrder(root, subtreeMapping, duplicate);
        
        // 遍歷Map，抓為true的subtree放入答案
        for (Map.Entry<String, Boolean> entry : duplicate.entrySet()) {
            if (entry.getValue() == true) {
                ans.add(subtreeMapping.get(entry.getKey()));
            }
        }
        
        return ans;
    }
    
    // 遞歸定義：求當前substring的編碼，同時看當前的subtree是否有duplicate，把新的資訊放入map中
    public String postOrder(TreeNode root, Map<String, TreeNode> subtreeMapping, Map<String, Boolean> duplicate) {
        // base case: 如果當前root為null, 回傳null
        if (root == null) {
            return "null, ";
        }
        
        // 分派給兒子，我要左右兒子幫我求left的subtree編碼及right的subtree編碼
        String left = postOrder(root.left, subtreeMapping, duplicate);
        String right = postOrder(root.right, subtreeMapping, duplicate);
        
        // 當前層要做的事，如果有左右兒子的編碼，我就可以求我這層的編碼
        String cur = left + right + root.val;
        
        // 如果這層的編碼有重複，設為true
        if (!duplicate.containsKey(cur)) {
            duplicate.put(cur, false);
        } else {
            duplicate.put(cur, true);
        }
        
        // 把當前subtree的root放入map中
        subtreeMapping.put(cur, root);
        
        return cur;
    }
}