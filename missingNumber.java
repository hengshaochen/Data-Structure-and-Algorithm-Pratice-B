// 法2可以把數組所有數字加起來，在算出0~n的sum, 0~n的和扣掉術組數字和 就是missing number
class Solution {
    public int missingNumber(int[] nums) {
        int len = nums.length;
        int total = len * (len + 1) / 2;
        int sum = 0;
        
        for (int cur : nums) {
            sum += cur;
        }
        
        return total - sum;
    }
}

// 法1: 就是把數組每個數字和0 ~ n 取XOR, 因為XOR有交換率，最後結果剩下那個數字就是missing number, 因為自己和自己XOR照理說會變成0.
class Solution {
    public int missingNumber(int[] nums) {
        // {0,1,2,3} XOR {0,1,3}
        int ans = 0;
        int i = 0;
        for (i = 0; i < nums.length; i++) {
            ans = ans ^ i ^ nums[i];
        }
        return ans ^ i;
    }
}