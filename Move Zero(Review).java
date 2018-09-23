// 原題[0,1,0,3,12] -> [1,3,12,0,0]
class Solution {
    public void moveZeroes(int[] nums) {
        // 要讓i左邊的都不為0
        // j找非0的
        int i = 0, j = 0;
        while (j < nums.length) {
            if (nums[j] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
            }
            j++;
        }
    }
}

// 變形, 把0移動到前面 [0,0,1,3,12]
class Solution {
    public void moveZeroes(int[] nums) {
        int i = nums.length - 1, j = nums.length - 1;
        while (i >= 0) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                j--;
            }
            i--;
        }
    }
}