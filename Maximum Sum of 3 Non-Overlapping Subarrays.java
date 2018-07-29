class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] ans = new int[3];
        if (nums == null || nums.length == 0) {
            return ans;
        }
        
        int len = nums.length;
        // Step1: 求kSubarraySum, 用sliding window, window size = k
        int[] kArraySum = new int[len - k + 1];
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum = sum + nums[i];
        }
        kArraySum[0] = sum;
        for (int i = k; i < len; i++) {
            kArraySum[i - k + 1] = kArraySum[i - k] - nums[i - k] + nums[i];
        }
        
        // Step2: 定義left[i]為kArraySum[i](包含i)的左邊之最大值的index
        int[] left = new int[kArraySum.length];
        left[0] = 0;
        for (int i = 1; i < left.length; i++) {
            // 如果left左邊一個的index值比當前小，把當前的放入left, 不然就放前一個的就好
            if (kArraySum[left[i - 1]] < kArraySum[i]) {
                // 當前idx放入left
                left[i] = i;
            } else {
                // 放前一個的
                left[i] = left[i - 1];
            }
        }
        
        
        int[] right = new int[kArraySum.length];
        right[right.length - 1] = right.length - 1;
        for (int i = right.length - 2; i >= 0; i--) {
            if (kArraySum[right[i + 1]] < kArraySum[i]) {
                right[i] = i;
            } else {
                right[i] = right[i + 1];
            }
        }
        
        // 初始化第一組解
        ans[0] = 0; ans[1] = k; ans[2] = k * 2;
        
        // Step3: 從區間[k ~ subArray.length - k)窮舉，找最大
        for (int i = k; i < kArraySum.length - k; i++) {
            int curAns = kArraySum[i] + kArraySum[left[i - k]] + kArraySum[right[i + k]];
            if (curAns > kArraySum[ans[0]] + kArraySum[ans[1]] + kArraySum[ans[2]]) {
                ans[0] = left[i - k];
                ans[1] = i;
                ans[2] = right[i + k];
            }
        }
        
        return ans;
    }
}