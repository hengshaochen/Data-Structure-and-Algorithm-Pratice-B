class Solution {
    public List<String> removeInvalidParentheses(String s) {
        // Step1: compute min number of ( and ) need to remove
        int l = 0, r = 0;
        for (Character c : s.toCharArray()) {
            if (l == 0 && c == ')') {
                r++;
            }
            if (c == '(') {
                l++;
            } else if (l != 0 && c == ')') {
                l--;
            }
        }
        
        List<String> ans = new ArrayList<>();
        dfs(0, l, r, s, ans);
        return ans;
    }
    
    public boolean isValid(String s) {
        int l = 0, r = 0;
        for (Character c : s.toCharArray()) {
            if (l < r) {
                return false;
            }
            if (c == '(') {
                l++;
            } else if (c == ')') {
                r++;
            }
        }
        return l == r;
    }
    
    public void dfs(int start, int l, int r, String s, List<String> ans) {
        // base case
        if (l == 0 && r == 0) {
            if (isValid(s)) {
                ans.add(s);
            }
        }
        
        // Step2: Try all possible way to remove r個右括號 和 l個左括號
        // 先從右括號開始移除，因移除左括號會invalid
        for (int i = start; i < s.length(); i++) {
            // 遇到非括號跳過
            if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                continue;
            }
            // 去重
            if (i != 0 && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }
            
            StringBuilder sb = new StringBuilder(s);
            if (r > 0) {
                sb.deleteCharAt(i);
                dfs(i, l, r - 1, sb.toString(), ans);
            } else if (l > 0) {
                sb.deleteCharAt(i);
                dfs(i, l - 1, r, sb.toString(), ans);
            }
        }
    }
}