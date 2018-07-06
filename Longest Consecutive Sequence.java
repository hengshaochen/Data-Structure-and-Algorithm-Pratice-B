class Solution {
    public int longestConsecutive(int[] nums) {
        int ans = 0;
        if (nums == null || nums.length == 0) {
            return ans;
        }
        Set<Integer> set = new HashSet<>();
        for (Integer cur : nums) {
            set.add(cur);
        }
        
        
        for (Integer cur : nums) {
            // 避免重複掃描，有可能已經掃到之前抓到連續LCS group的成員，已經不在set中就不用在掃一次
            if (set.contains(cur)) {
                int left = cur - 1, right = cur + 1;

                // 盡量往左邊找, 找到此時的left不存在於set中（注意：不是index, 而是value)
                while (set.contains(left)) {
                    set.remove(left);
                    left--;
                }
                // 盡量往右邊找
                while (set.contains(right)) {
                    set.remove(right);
                    right++;
                }
                ans = Math.max(ans, right - left - 1);
            }
        }
        
        return ans;
    }
}