class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            int curSum = numbers[i] + numbers[j];
            if (curSum == target) {
                int[] ans = new int[2];
                ans[0] = i + 1;
                ans[1] = j + 1;
                return ans;
            } else if (curSum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[2];
    }
}