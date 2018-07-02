class Solution {
    public int monotoneIncreasingDigits(int N) {
        // int to char array
        char [] nums = String.valueOf(N).toCharArray();
        int j = nums.length;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] >= nums[i - 1]) {
                continue;
            }
            // 到這一步都是nums[i] > nums[i - 1] 後面一位比前面一位大，不符合規定，把前面一位-1
            nums[i - 1] -= 1;
            j = i;
        }
        
        // 把j和j以後的都變成9
        for (int i = j; i < nums.length; i++) {
            nums[i] = '9';
        }
        
        // char array --> string --> int
        return Integer.parseInt(new String(nums));
    }
}