import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class Solution {
    class TreeNode {
        int val;
        List<TreeNode> child;
        public TreeNode(int val) {
            child = new ArrayList<>();
            this.val = val;
        }
    }
    
    public static void main(String args[]) throws Exception {
        /* Enter yourcode here. Read input from STDIN. Print output to STDOUT */
        new Solution();
    }
    
    
    public Solution() {
        int n = 7, k = 3;
        TreeNode root = buildTree(n, k);
        System.out.println(root.val);
        for (int i = 0; i < k; i++) {
            System.out.println(root.child.get(i).val);
        }
        for (int i = 0; i < k; i++) {
            System.out.println(root.child.get(0).child.get(i).val);
        }
        
    }
    
    public TreeNode buildTree(int n, int k) {
        if (n % k != 1) {
            return null;
        }
        
        Queue<TreeNode> q = new LinkedList<>();
        int count = 0;
        TreeNode root = new TreeNode(++count);
        q.add(root);
        
        while (!q.isEmpty()) {
            int qsize = q.size();
            for (int i = 0; i < qsize; i++) {
                TreeNode cur = q.remove();
                for (int j = 0; j < k; j++) {
                    // exit condition
                    if (count == n) {
                        break;
                    }
                    TreeNode newChild = new TreeNode(++count);
                    cur.child.add(newChild);
                    q.add(newChild);
                }
            }
        }
        
        return root;
    }
    
}