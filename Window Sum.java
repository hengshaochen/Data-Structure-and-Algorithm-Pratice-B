// Time: O(n)
public class Solution {
    /**
     * @param nums a list of integers.
     * @return the sum of the element inside the window at each moving.
     */
    public int[] winSum(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length < k || k <= 0)
            return new int[0];

        int[] sums = new int[nums.length - k + 1];

        // 先建立第一組window sum的答案
        for (int i = 0; i < k; i++) {
            sums[0] += nums[i];
        }

        // 後面就是把前一組的window sum答案 + 當前後面要加入的數字 - 前面要扣掉的數字 
        // 就是sliding window的概念
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] - nums[i - 1] + nums[i + k-1];
        }
        return sums;
    }
}