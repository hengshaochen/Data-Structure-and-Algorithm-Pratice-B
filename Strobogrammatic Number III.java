class Solution {
    public int strobogrammaticInRange(String low, String high) {
        List<String> ans = new ArrayList<>();
        for (int i = low.length(); i <= high.length(); i++) {
            // 每次helper都會產生一個List<String> 這時要調用addAll加入(Collections)
            ans.addAll(helper(i, i));
        }
        
        // 去除不在範圍內的
        int count = 0;
        for (String num : ans) {
            long cur = Long.parseLong(num);
            if (cur >= Long.parseLong(low) && cur <= Long.parseLong(high)) {
                count++;
            }
        }
        return count;
    }

    public List<String> helper(int n, int m) {
        // base case
        if (n == 0) {
            return new ArrayList<String>(Arrays.asList(""));
        }
        if (n == 1) {
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));
        }
        
        // 切成子問題
        
        // 子問題（上一層）
        List<String> list = helper(n - 2, m);
            
        // 這一層，要回傳的答案
        List<String> ans = new ArrayList<>();
        
        // 本層要做的事情
        for (int i = 0; i < list.size(); i ++) {
            String cur = list.get(i);
            // 若不是最終層，就可以加入0在兩側
            if (m != n) {
                ans.add("0" + cur + "0");
            }
            ans.add("1" + cur + "1");
            ans.add("6" + cur + "9");
            ans.add("8" + cur + "8");
            ans.add("9" + cur + "6");
        }
        
        return ans;
    }

}

// 優化判斷是否在range中：
            if ( (num.length() == low.length() && num.compareTo(low) < 0) ||
                  num.length() == high.length() && num.compareTo(high) > 0) {
                continue;
            }