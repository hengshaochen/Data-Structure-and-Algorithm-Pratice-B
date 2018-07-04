// O(N^2)
class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 不同i去重[1,1,1,1] , i = 0 和 i = 1時的去重複
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // 同一個i內去重, [1,1,1,1] , 當i = 0 , j = 1 不用去重, i = 0, j = 2去重
                if (nums[j] == nums[i] && j != i + 1) {
                    continue;
                }
                long curDiff = Math.abs(nums[i] - nums[j]);
                if (curDiff == k) {
                    ans++;
                    break;
                } else if (curDiff > k) {
                    break;
                }
            }
        }
        return ans;
    }
}

// O(N)
class Solution {
    public int findPairs(int[] nums, int k) {
        // 注意要加特判，當k < 0不可能有答案，因為是求兩數之間的距離
        if (nums == null || nums.length == 0 || k < 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        // 1.先把所有元素丟到map統計出現次數
        for (int cur : nums) {
            map.put(cur, map.getOrDefault(cur, 0) + 1);
        }
        
        // 2. 掃map, 分兩種case, Case1: input k = 0 只要找該元素重複次數出現超過兩次的有幾個
        // Case2: input k != 0, 則要組成pair, 相差為k
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                // Case1
                if (entry.getValue() >= 2) {
                    ans++;
                }
            } else {
                // Case2
                // map中假設有[a,b]兩個元素, 求兩個pair差值為k. 如果a - b == k 即可
                // 因此若當前掃到b, b + k若存在於map中即可代表找到(a,b) pair的差為k
                if (map.containsKey(entry.getKey() + k)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}