class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Brute Force O(n^3)
        // 竟然Accept，但是可以優化成O(n^2)。
        // 發生的bug: 初始化minDistanceSum，如果用Integer.MAX_VALUE初始化會發生OVERFLOW
        
        // 特別判斷，萬一數組長度不足3
        if (nums.length < 3) {
            return 0;
        }
        int minDistanceSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int curSum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(curSum - target) < Math.abs(minDistanceSum - target)) {
                        minDistanceSum = curSum;
                    }
                }
            }
        }
        return minDistanceSum;
    }
}

// 法2:
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // 排序 3Sum套路 O(n^2)
        Arrays.sort(nums);
        
        // 特判斷
        if (nums.length < 3) {
            return 0;
        }
        
        // 定義為：3Sum closet的答案
        int minDistanceSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int curSum = nums[i] + nums[j] + nums[k];
                if (Math.abs(curSum - target) < Math.abs(minDistanceSum - target)) {
                    minDistanceSum = curSum;
                }
                
                // 指針走動
                if (curSum < target) {
                    j++;
                } else if (curSum > target) {
                    k--;
                } else {
                    // 如果curSum == target, 代表直接找到答案
                    return curSum;
                }
            }
        }
        return minDistanceSum;
    }
}