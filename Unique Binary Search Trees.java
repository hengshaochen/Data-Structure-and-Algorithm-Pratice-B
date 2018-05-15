// 96. Unique Binary Search Trees
class Solution {
     public int numTrees(int n) {
        int [] dp = new int[n+1];
        dp[0]= 1;
        dp[1] = 1;
        // 每次算一個level
        for(int level = 2; level <=n; level++) {
            // 每個level右到左掃一遍
            for(int root = 1; root<=level; root++) {
                dp[level] += dp[level-root]*dp[root-1];
            }
        }
        return dp[n];
    }
}

// 95. Unique Binary Search Trees II
