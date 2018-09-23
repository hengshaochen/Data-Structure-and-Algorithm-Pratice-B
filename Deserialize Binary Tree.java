import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TreeNode {
    char val;
    TreeNode left, right;
    TreeNode(char val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class Solution {
    public static void main(String args[] ) throws Exception {
        /* Enter yourcode here. Read input from STDIN. Print output to STDOUT */
        new Solution();
    }
    
    
    public Solution() {
        //String s = "(2, (3,N,N), (4,N,N))";
        //String s = "(2, 3, N)";
        String s = "(2, (3, (2, N, N), N), (4, N, N))";
        int[] idx = new int[1];
        idx[0] = 0;
        TreeNode root = dfs(s, idx);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.left.left.val);
        System.out.println(root.right.val);
    }
    
    public TreeNode dfs(String s, int[] idx) {
        int cnt = 0;
        
        TreeNode root = null;
        
        while (idx[0] < s.length()) {
            if (s.charAt(idx[0]) == '(') {
                idx[0]++;
                if (cnt == 0) {
                    root = dfs(s, idx);
                } else if (cnt == 1) {
                    root.left = dfs(s, idx);
                } else {
                    root.right = dfs(s, idx);
                }
            } else if (s.charAt(idx[0]) == ',') {
                idx[0]++;
                cnt++;
            } else if (s.charAt(idx[0]) == ')') {
                idx[0]++;
                break;
            } else if (s.charAt(idx[0]) == ' ') {
                idx[0]++;
            } else {
                if (cnt == 0) {
                    root = s.charAt(idx[0]) == 'N' ? null : new TreeNode(s.charAt(idx[0]));
                    idx[0]++;
                } else if (cnt == 1) {
                    root.left = s.charAt(idx[0]) == 'N' ? null : dfs(s, idx);
                    idx[0]++;
                } else {
                    root.right = s.charAt(idx[0]) == 'N' ? null : dfs(s, idx);
                    idx[0]++;
                }
            }
        }
        return root;
    }
}