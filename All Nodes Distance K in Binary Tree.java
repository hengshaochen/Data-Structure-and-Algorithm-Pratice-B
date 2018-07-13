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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        List<Integer> ans = new ArrayList<>();
        // 1. 把Tree建立成Undirect Graph
        Map<TreeNode, List<TreeNode>> g = new HashMap<>();
        buildGraph(null, root, g);
        
        // 2. 從Target開始走BFS, 走K步
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(target);
        visited.add(target);
        while (!q.isEmpty() && K > 0) {
            K--;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                List<TreeNode> neighbors = g.get(cur);
                for (TreeNode neighbor : neighbors) {
                    // 如果已經走訪過該點, 直接skip, 避免重複走訪
                    if (visited.contains(neighbor)) {
                        continue;
                    }
                    q.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        
        // 剩下queue的treenode都是距離k步的node
        while (!q.isEmpty()) {
            ans.add(q.remove().val);
        }
        return ans;
    }
    
    // traverse tree來建立圖
    public void buildGraph(TreeNode parent, TreeNode child, Map<TreeNode, List<TreeNode>> g) {
        // 如果沒建立先建立
        if (!g.containsKey(parent)) {
            g.put(parent, new ArrayList<>());
        }
        if (!g.containsKey(child)) {
            g.put(child, new ArrayList<>());
        }
        
        if (parent != null) {
            // 如果parent存在, 把parent和孩子建立edge
            g.get(parent).add(child);
            g.get(child).add(parent);
        }
        if (child.left != null) {
            buildGraph(child, child.left, g);
        }
        if (child.right != null) {
            buildGraph(child, child.right, g);
        }
    }
}