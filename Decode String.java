class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] cur = new int[1];
        cur[0] = 0;
        return dfs(cur, s);
    }
    
    public String dfs(int[] cur, String s) {
        StringBuilder ans = new StringBuilder();
        int mul = 0;
        while (cur[0] < s.length()) {
            char curChar = s.charAt(cur[0]);
            if (curChar == '[') {
                cur[0] += 1;
                String subProblemAns = dfs(cur, s);
                for (int i = 0; i < mul; i++) {
                    ans.append(subProblemAns);
                }
                mul = 0;    // 每做完一個子問題的append mul倍，記得把mul歸零
            } else if (curChar == ']') {
                return ans.toString();
            } else if (curChar >= '0' && curChar <= '9') {
                mul = mul * 10 + (curChar - '0');
            } else {
                // 如果是一般英文字母
                ans.append(curChar);
            }
            cur[0]++;
        }
        
        return ans.toString();
    }
}