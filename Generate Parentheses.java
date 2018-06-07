class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, n, "", ans);
        return ans;
    }
    
    public void dfs(int leftRemain, int rightRemain, String cur, List<String> ans) {
        // base case
        if (leftRemain > rightRemain || leftRemain < 0 || rightRemain < 0) {
            return;
        }
        if (leftRemain == 0 && rightRemain == 0) {
            ans.add(cur);
            return;
        }
        
        dfs(leftRemain - 1, rightRemain, cur + '(', ans);
        dfs(leftRemain, rightRemain - 1, cur + ')', ans);
        
    }
}