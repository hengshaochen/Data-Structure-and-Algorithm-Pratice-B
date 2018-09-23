// "static void main" must be defined in a public class.
public class Main {
    class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }
    public static void main(String[] args) {
        new Main();
    }
    
    public Main() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(7);
        root.left.right = new TreeNode(8);
        root.left.right.right = new TreeNode(9);
        
        System.out.println(findSuc(root, root.left, root.left.right.right));
    }
    
    boolean findSuc(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null || A == null || B == null) {
            return false;
        }
        
        TreeNode rootA = findA(root, A);
        /*if (rootA == null) {
            // 找不到rootA
            return false;
        }*/
        return findB_in_rootA(rootA, B);
    }
    
    TreeNode findA(TreeNode root, TreeNode A) {
        if (root == null) {
            return null;
        }
        
        if (root == A) {
            return root;
        }
        TreeNode left = findA(root.left, A);
        TreeNode right = findA(root.right, A);
        
        if (left != null) {
            return left;
        } else if (right != null) {
            return right;
        } else {
            return null;
        }
    }
    
    boolean findB_in_rootA(TreeNode rootA, TreeNode B) {
        if (rootA == null) {
            return false;
        }
        
        if (rootA == B) {
            return true;
        }
        boolean left = findB_in_rootA(rootA.left, B);
        boolean right = findB_in_rootA(rootA.right, B);
        
        return left || right;
    }
}