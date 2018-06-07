class Solution {
    public int findMaxLength(int[] nums) {
        // step1: 先把0設為-1
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }
        
        // step2: traverse + map讓O(n^2) --> O(n), 有點類似two-sum 每次只要用map用O(1)查找該sum是否出現過
        // map<sum, index>
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        // ***注意：map要先初始化put(0, -1)進去，依照定義，當index為-1時， +1和-1的個數是相等的，因為都是0個
        sumToIndex.put(0, -1);
        int sum = 0;
        int ans = 0;
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumToIndex.containsKey(sum)) {
                // 代表現在i這個位置的數組，先前出現過一樣的sum，只要把兩個index相減，就會等於0，就符合題目要求，代表有相同1和-1
                ans = Math.max(ans, i - sumToIndex.get(sum));
            } else {
                sumToIndex.put(sum, i);
            }
        }
        
        return ans;
    }
}