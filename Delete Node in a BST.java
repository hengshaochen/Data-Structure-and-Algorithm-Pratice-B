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
    public TreeNode deleteNode(TreeNode root, int key) {
        // base case
        if (root == null) {
            return root;
        }
        
        if (key > root.val) {
            // key比當前root大，往右進行刪除
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            // key比當前root小，往左進行刪除
            root.left = deleteNode(root.left, key);
        } else {
            // 當root == key, 代表找到要刪除的點，進入case2，有三種子case
            
            // 2.a: 當當前root為leaf, 直接刪除
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.left != null && root.right != null) {
                // 2.c 最難的，若有左右子樹，則必須取右子樹的最小值來當新的root取代原本root，因此現在會有兩個重複的val,刪除右子樹的
                TreeNode newRoot = root.right;
                // 要找最小的，就一直往左走，因為是BST
                while (newRoot.left != null) {
                    newRoot = newRoot.left;
                }
                root.val = newRoot.val;
                // 刪除重複的val
                root.right = deleteNode(root.right, newRoot.val);
            } else {
                // 2.b : 其中一個為null, 把其中一個不為null的拉上來
                TreeNode newRoot = root.left == null ? root.right : root.left;
                root.left = null;
                root.right = null;
                return newRoot;
            }   
        }
        return root;
    }
}