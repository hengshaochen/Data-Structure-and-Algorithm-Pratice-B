// BUG版本，BUG原因請看筆記
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
    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 0;
        if (root == null) {
            return maxWidth;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            int leftIndex = 0, rightIndex = 0, counter = 0;
            boolean leftOccur = false;
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                // 處理左子樹
                if (cur.left != null) {
                    q.add(cur.left);
                    if (leftOccur == false) {
                        leftIndex = counter;
                        leftOccur = true;
                    }
                    rightIndex = counter;
                }
                // 處理右子樹
                if (cur.right != null) {
                    q.add(cur.right);
                    if (leftOccur == false) {
                        leftIndex = counter + 1;
                        leftOccur = true;
                    }
                    rightIndex = counter + 1;
                }
                // 每個node有兩個兒子，把counter + 2
                counter += 2;
            }
            
            // 每層結束後，更新MAX
            System.out.println(leftIndex + " " + rightIndex);
            maxWidth = Math.max(maxWidth, (rightIndex - leftIndex) + 1 );
        }
        return maxWidth;
    }
}

// 正解BFS
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.AbstractMap;

class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = 0;
        Queue<Map.Entry<TreeNode, Integer>> q = new LinkedList<>();
        q.add(new AbstractMap.SimpleEntry(root, 1));
        
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            int left = q.peek().getValue();
            int right = left; // right會不斷update
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.peek().getKey();
                int curValue = q.peek().getValue();
                right = curValue; // right盡量往右邊走
                q.remove();
                if (cur.left != null) {
                    q.add(new AbstractMap.SimpleEntry(cur.left, curValue * 2));
                }
                if (cur.right != null) {
                    q.add(new AbstractMap.SimpleEntry(cur.right, curValue * 2 + 1));
                }
            }
            maxWidth = Math.max(maxWidth, right - left + 1);
        }
        
        return maxWidth;
    }
}

// 正解DFS
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
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> leftMostEveryLevel = new ArrayList<>();
        return dfs(root, 0, 1, leftMostEveryLevel);
    }
    public int dfs(TreeNode cur, int depth, int id, List<Integer> leftMostEveryLevel) {
        if (cur == null) {
            return 0;
        }
        // 和levelOrder套路一樣，如果第一次遇到這層，把當前的node id放到對應層數
        if (depth == leftMostEveryLevel.size()) {
            leftMostEveryLevel.add(id);
        }
        int left = dfs(cur.left, depth + 1, id * 2, leftMostEveryLevel);
        int right = dfs(cur.right, depth + 1, id * 2 + 1, leftMostEveryLevel);
        int curWidth = id - leftMostEveryLevel.get(depth) + 1;
        return Math.max(curWidth, Math.max(left, right));
    }
}
