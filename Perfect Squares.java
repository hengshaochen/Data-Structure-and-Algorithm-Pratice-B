// Perfect Squares TLE
class Solution {
    int ans;
    public int numSquares(int n) {
        ans = n;
        dfs(n, 0);
        
        return ans;
    }
    
    public void dfs(int cur, int curAns) {
        if (cur < 0) {
            return;
        }
        if (cur == 0) {
            //System.out.println(cur + " " + curAns);
            ans = Math.min(curAns, ans);
            return;
        }
        
        // 計算所有小於cur的perfect number
        for (int i = cur; i >= 1; i--) {
            //System.out.println("I:" + i);
            int iSqrt = (int)Math.sqrt(i);
            if (iSqrt * iSqrt == i) {
                //System.out.println("i:" + i + " cur:" + cur + " curAns:" + curAns);
                // 符合條件代表為perfect number
                //cur = cur - i;
                //curAns = curAns + 1;
                dfs(cur - i, curAns + 1);
            }
        }
    }
}

// Accept
class Solution {
    public int numSquares(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = dfs(n, map);
        return ans;
    }
    
    public int dfs(int cur, HashMap<Integer, Integer> map) {
        if (map.containsKey(cur)) {
            return map.get(cur);
        }
        if (cur <= 0) {
            return 0;
        }
        
        // 計算所有小於cur的perfect number
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i * i <= cur; i++) {
                // 符合條件代表為perfect number
                //cur = cur - i;
                //curAns = curAns + 1;
            ans = Math.min(ans, 1 + dfs(cur - (i * i), map));
            
        }
        map.put(cur, ans);
        return map.get(cur);
    }
}