// O(N^2)
class Solution {
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == k) {
                ans += 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == k) {
                    ans += 1;
                }
            }
        }
        
        return ans;
    }
}

// O(N)
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int ans = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum == k) {
                ans += 1;
            }
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }
            
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            } else {
                map.put(sum, map.get(sum) + 1);
            }
        }
        
        return ans;
    }
}