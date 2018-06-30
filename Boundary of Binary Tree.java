// 正確版
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
    List<Integer> ans = new ArrayList<>();
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) {
            return ans;
        }
        
        ans.add(root.val);
        leftBoundary(root.left);
        leave(root.left);
        leave(root.right);
        rightBoundary(root.right);
        
        return ans;
    }
    
    // 定義：找leftBoundary, 傳入是root的左子樹root，如果當前為空，或當前為leaf，直接return, 如果有值，加入答案，然後有左往左走，左沒有往右走
    public void leftBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        // 左邊是越淺層的就先加入, 類似preorder
        ans.add(root.val);
        if (root.left != null) {
            leftBoundary(root.left);
        } else {
            leftBoundary(root.right);
        }
    }
    
    // 定義：找rightBoundary, 和left的差異只有越深層的先加入 類似dfs
    public void rightBoundary(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }
        
        if (root.right != null) {
            rightBoundary(root.right);
        } else {
            rightBoundary(root.left);
        }
        // 最深層的先加入(dfs) , 因為rightBoundary是下往上走
        ans.add(root.val);
    }
    
    // 定義：找leaf node
    public void leave(TreeNode root) {
        if (root == null) {
            return;
        }
        
        // 往深層走
        leave(root.left);
        leave(root.right);
        
        if (root.left == null && root.right == null) {
            ans.add(root.val);
        }
    }
}


// 錯誤程式碼，有BUG
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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> rightList = new ArrayList<>();
        List<Integer> leafList = new ArrayList<>();
        while (!q.isEmpty()) {
            int qsize = q.size();
            // 每層清除leafList, 因為只需要最後面那層的就可以了，因為bfs事先不知道有幾層
            leafList.clear();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                // 代表是這層最左邊的元素 --> 直接加入ans
                if (i == 0) {
                    ans.add(cur.val);
                } else if (i == qsize - 1) {
                    // 代表最右邊元素 --> 先加入rightList
                    rightList.add(cur.val);
                } else {
                    // 其他中間的元素就先加入leafList
                    leafList.add(cur.val);
                }
                
                // 正常bfs
                if (cur.left != null) {
                    q.add(cur.left);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                }
            }
        }
        
        // bfs結束後，做post-processing, 把ans + leafList + rightList.reverse()
        Collections.reverse(rightList);
        ans.addAll(leafList);
        ans.addAll(rightList);
        return ans;
    }
}