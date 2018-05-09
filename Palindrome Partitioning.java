class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        
        dfs(0, new ArrayList<String>(), s, ans);
        return ans;
    }
    
    public void dfs(int start, List<String> cur, String s, List<List<String>> ans) {
        // base case: 跑到最後的一定就是符合的，不行的那條分支就不會繼續走下去
        if (start == s.length()) {
            ans.add(new ArrayList<String>(cur));
            return;
        }
        
        for (int i = start; i < s.length(); i++) {
            String subString = s.substring(start, i + 1);
            if (valid(subString)) {
                cur.add(subString);
                dfs(i + 1, cur, s, ans);
                cur.remove(cur.size() - 1);
            }
        }
    }
    
    public boolean valid (String s) {
        if (s.length() == 0) {
            return false;
        }
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}