// 法1: O(nlogn)
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        
        for (int i = 2; i < nums.length; i += 2) {
            int buf = nums[i - 1];
            nums[i - 1] = nums[i];
            nums[i] = buf;
        }
        
    }
}

// 法2: O(n)
class Solution {
    public void wiggleSort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 == 0 && nums[i] > nums[i - 1])) {
                int buf = nums[i - 1];
                nums[i - 1] = nums[i];
                nums[i] = buf;
            }
        }
    }
}