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

// DFS + Memorization
