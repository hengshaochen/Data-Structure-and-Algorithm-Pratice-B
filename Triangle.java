// DFS: TLE
class Solution {
    int ans;
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        ans = Integer.MAX_VALUE;
        dfs(1, 0, triangle.get(0).get(0), triangle);
        return ans;
    }
    
    void dfs(int nextLevel, int curPos, int curSum, List<List<Integer>> triangle) {
        // base case: 如果nextLevel等於size代表超界，代表已到bottom，值經更新ans，然後return
        if (nextLevel == triangle.size() ) {
            ans = Math.min(ans, curSum);
            return;
        }
        
        dfs(nextLevel + 1, curPos, curSum + triangle.get(nextLevel).get(curPos), triangle);
        dfs(nextLevel + 1, curPos + 1, curSum + triangle.get(nextLevel).get(curPos + 1), triangle);
    }
}

// DFS (Divide and Conquar)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return dfs(0, 0, triangle);
    }
    
    int dfs(int curLevel, int curPos, List<List<Integer>> triangle) {
        if (curLevel == triangle.size() ) {
            return 0;
        }
        
        return triangle.get(curLevel).get(curPos) + Math.min(dfs(curLevel + 1, curPos, triangle), 
                                                             dfs(curLevel + 1, curPos + 1, triangle));
    }
}

// DFS + Memorization
class Solution {
    int[][] hash;
    public int minimumTotal(List<List<Integer>> triangle) {
        
        hash = new int[triangle.size()][triangle.size()];
        // Init Hash Matrix
        for (int[] row : hash) {
            Arrays.fill(row, -1);
        }
        return dfs(0, 0, triangle);
    }
    
    int dfs(int curLevel, int curPos, List<List<Integer>> triangle) {
        if (curLevel == triangle.size() ) {
            return 0;
        }
        
        // 代表這個子問題已經解過了
        if (hash[curLevel][curPos] != -1) {
            return hash[curLevel][curPos];
        }
        
        hash[curLevel][curPos] = triangle.get(curLevel).get(curPos) + Math.min(dfs(curLevel + 1, curPos, triangle), 
                                                                               dfs(curLevel + 1, curPos + 1, triangle));
        return hash[curLevel][curPos];
    }
}

// 一般DP, 定義DP[curLevel][curPos]代表該座標的min path value
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        
        int[][] dp = new int[triangle.size()][triangle.size()];
        
        // init: 先把最後一層填上去，以免後續轉移式越界
        int len = triangle.size(); 
        for (int i = 0; i < len; i++) {
            dp[len - 1][i] = triangle.get(len - 1).get(i);
        }
        
        // dp轉移, bottom-up
        for (int curLevel = len - 2; curLevel >= 0; curLevel--) {
            for (int curPos = 0; curPos <= curLevel; curPos++) {
                dp[curLevel][curPos] = triangle.get(curLevel).get(curPos) + Math.min(dp[curLevel + 1][curPos],
                                                                                     dp[curLevel + 1][curPos + 1]);
            }
        }
        
        return dp[0][0];
    }
}

// DP，但只用O(n)，由下往上，但因為每次都只需要保留當前最後一層即可，所以不用開二維array，用一維即可，可以一直蓋掉，
// 舊的就都不用了，只需保留最新那層
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0) {
            return 0;
        }
        
        int[]dp = new int[triangle.size()];
        
        // init: 先把最後一層填上去，以免後續轉移式越界
        int len = triangle.size(); 
        for (int i = 0; i < len; i++) {
            dp[i] = triangle.get(len - 1).get(i);
        }
        
        // dp轉移, bottom-up
        for (int curLevel = len - 2; curLevel >= 0; curLevel--) {
            for (int curPos = 0; curPos <= curLevel; curPos++) {
                dp[curPos] = triangle.get(curLevel).get(curPos) + Math.min(dp[curPos], dp[curPos + 1]);
            }
        }
        
        return dp[0];
    }
}
